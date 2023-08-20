package com.gilaraji.qrispaymentapp.presentation.ui.addQris


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gilaraji.qrispaymentapp.presentation.ui.MainActivity
import com.gilaraji.qrispaymentapp.presentation.utils.getUser
import com.gilaraji.qrispaymentapp.presentation.utils.splitToQRScan
import com.gilaraji.qrispaymentapp.presentation.utils.updateUser
import com.gilaraji.qrispaymentapp.domain.models.QrisModels
import com.gilaraji.qrispaymentapp.domain.models.UserModels
import com.gilaraji.qrispaymentapp.presentation.ui.home.AddButton
import com.gilaraji.qrispaymentapp.presentation.ui.home.CardQRView
import com.gilaraji.qrispaymentapp.presentation.ui.theme.QrisPaymentAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScanQRActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var userData by remember { mutableStateOf(getUser(this@ScanQRActivity)) }
            val viewModel: AddQrisViewModel = viewModel()
            QrisPaymentAppTheme {

                MainScreen { result ->
                    if (userData?.saldo!! >= result.nominalTransaksi!!.toInt()) {
                        val userupdate = UserModels(
                            userData?.id,
                            userData?.name,
                            userData?.saldo!! - result.nominalTransaksi!!.toInt(),
                            userData?.date
                        )
                        updateUser(this@ScanQRActivity, userupdate)
                        userData = getUser(this@ScanQRActivity)
                        viewModel.saveData(result).observe(this) {
                            Toast.makeText(this, "Transaksi berhasil", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        }

                    } else {
                        Toast.makeText(this, "saldo tidak cukup", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }

                }
            }
        }
    }
}
@Preview
@Composable
fun MainScreen(
    onClick: (QrisModels) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var scantext by remember { mutableStateOf("") }
        CameraPreview(
            modifier = Modifier
        ) {
            scantext = it
            Log.e("QrisScan", it)
        }

        if (scantext.splitToQRScan() != null) {
            CardQRView(
                modifier = Modifier.padding(end = 16.dp, start = 16.dp), scantext.splitToQRScan()
            )
            AddButton {
                scantext.splitToQRScan()?.let {
                    onClick(it)
                }

            }
        }
    }
}




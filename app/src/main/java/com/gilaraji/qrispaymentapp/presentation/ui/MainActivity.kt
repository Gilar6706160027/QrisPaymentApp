package com.gilaraji.qrispaymentapp.presentation.ui


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gilaraji.qrispaymentapp.presentation.utils.getTimeDevide
import com.gilaraji.qrispaymentapp.presentation.utils.getUser
import com.gilaraji.qrispaymentapp.presentation.utils.saveUser
import com.gilaraji.qrispaymentapp.presentation.utils.updateUser
import com.gilaraji.qrispaymentapp.domain.models.QrisModels
import com.gilaraji.qrispaymentapp.domain.models.UserModels
import com.gilaraji.qrispaymentapp.presentation.ui.home.QrisViewModel
import com.gilaraji.qrispaymentapp.presentation.ui.home.HomeScreen
import com.gilaraji.qrispaymentapp.presentation.ui.addQris.MyScreen
import com.gilaraji.qrispaymentapp.presentation.ui.addQris.ScanQRActivity
import com.gilaraji.qrispaymentapp.presentation.ui.home.Toolbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: QrisViewModel = viewModel()

            MyScreen()
            var userData by remember { mutableStateOf(getUser(this@MainActivity)) }

            val dataList = remember { mutableStateListOf<QrisModels>()}
            if (userData?.name == null) {
                saveUser(
                    this@MainActivity,
                    UserModels(
                        1, "Gilar Aji Pangestu",
                        500000,
                        getTimeDevide()
                    )
                )
                userData = getUser(this@MainActivity)
            }

            viewModel.getData().observe(this){
                it.data?.let {
                    dataList.addAll(it)
                    Log.e("Gilar Set data", it.size.toString())
                }
            }

            Toolbar()
            HomeScreen(
                accountInfo = userData!!, dataList,
                onClick = {
                    startActivity(Intent(this, ScanQRActivity::class.java))
                }) {
                val userupdate = UserModels(
                    userData?.id,
                    userData?.name,
                    500000 + userData?.saldo!!,
                    userData?.date
                )
                updateUser(this@MainActivity, userupdate)
                userData = getUser(this@MainActivity)
            }

            BackHandler(
                enabled = true
            ) {
                finish()
            }

        }
    }


}






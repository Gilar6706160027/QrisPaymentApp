package com.gilaraji.qrispaymentapp.presentation.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gilaraji.qrispaymentapp.R
import com.gilaraji.qrispaymentapp.domain.models.QrisModels
import com.gilaraji.qrispaymentapp.domain.models.UserModels

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Toolbar(){
    Surface(
    ) {
        Column() {
            TopAppBar(title = { Text(text = "Qris Payment") })
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HomeScreen(
    accountInfo: UserModels,
    listTran: SnapshotStateList<QrisModels>,
    onClick: () -> Unit,
    addSaldo: () -> Unit
) {

    Column(modifier = Modifier.fillMaxSize()) {
        Toolbar()
        Surface(
            shape = RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp),
            border = BorderStroke(2.dp, Color.Blue),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = "Hello, ${accountInfo.name}!",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Saldo: Rp ${accountInfo.saldo}",
                        style = MaterialTheme.typography.bodySmall
                    )
                    IconButton(
                        onClick = {
                            addSaldo()
                        },
                        modifier = Modifier
                            .size(25.dp)
                    ) {
                        Icon(
                            modifier = Modifier.fillMaxSize(),
                            painter = painterResource(id = R.drawable.baseline_add_box_24), // Ganti dengan sumber daya ikon Anda
                            contentDescription = "Add Saldo"
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Tanggal pembuatan akun: ${accountInfo.date}",
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
        Column(modifier = Modifier.weight(1f)) {

            ItemList(listTran)

        }

        AddButton {
            onClick()
        }

    }


}
@Preview(showBackground = true)
@Composable
fun AddButton(onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .clickable {
                onClick()
            }
            .fillMaxWidth()
            .padding(16.dp),
        color = MaterialTheme.colorScheme.surface,
    ) {
        Button(
            onClick = {
                onClick()
            },
        ) {
            Text(text = "Add", style = MaterialTheme.typography.bodyMedium)
        }
    }
}


@Composable
fun ItemList(listTran: SnapshotStateList<QrisModels>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        content = {
            items(listTran) { item ->
                ListItem(item)
            }
        }
    )
}

@Composable
fun ListItem(item: QrisModels) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.surface
    ) {
        CardQRView(modifier = Modifier.padding(top = 8.dp, end = 16.dp, start = 16.dp), data = item)
    }
}



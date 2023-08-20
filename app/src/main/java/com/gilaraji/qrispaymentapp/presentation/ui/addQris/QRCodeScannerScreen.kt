package com.gilaraji.qrispaymentapp.presentation.ui.addQris

import androidx.compose.material3.Text

import android.Manifest
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.gilaraji.qrispaymentapp.presentation.utils.PermissionRequestDialog

@Composable
fun MyScreen() {
    var hasCameraPermission by remember { mutableStateOf(false) }

    // Fungsi untuk menghandle hasil permintaan izin kamera
    val onCameraPermissionResult: (Boolean) -> Unit = { isGranted ->
        hasCameraPermission = isGranted
    }

    // Memanggil fungsi PermissionRequestDialog untuk meminta izin kamera
    PermissionRequestDialog(
        permission = Manifest.permission.CAMERA, // Menggunakan konstanta dari Android Manifest
        onResult = onCameraPermissionResult
    )

    // Tampilkan UI berdasarkan izin kamera yang diizinkan atau tidak
    if (hasCameraPermission) {
        // Tampilkan komponen yang memerlukan izin kamera di sini
        Text(text = "Izin kamera diberikan!")
    } else {
        Text(text = "Tidak diizinkan untuk mengakses kamera.")
    }
}

@Preview
@Composable
fun PreviewMyScreen() {
    MyScreen()
}
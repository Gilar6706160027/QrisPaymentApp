package com.gilaraji.qrispaymentapp.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class QrisModels(
    var bankName: String? = null,
    @PrimaryKey var idTransaksi: String = "",
    var namaMerchant: String? = null,
    var nominalTransaksi: String? = null
)

class InvalidNoteException(message: String) : Exception(message)

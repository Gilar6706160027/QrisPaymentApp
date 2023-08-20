package com.gilaraji.qrispaymentapp.domain.usecase

import com.gilaraji.qrispaymentapp.domain.abstraction.QrisRepository
import com.gilaraji.qrispaymentapp.domain.models.InvalidNoteException
import com.gilaraji.qrispaymentapp.domain.models.QrisModels

class AddQrisPayment(
    private val repository: QrisRepository
) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(qrisModels: QrisModels) {
        if (qrisModels.bankName!!.isBlank()) {
            throw InvalidNoteException("Please enter a note title")
        }
        if (qrisModels.idTransaksi.isBlank()) {
            throw InvalidNoteException("Please enter note content")
        }
        if (qrisModels.namaMerchant!!.isBlank()) {
            throw InvalidNoteException("Please enter note content")
        }
        if (qrisModels.nominalTransaksi!!.isBlank()) {
            throw InvalidNoteException("Please enter note content")
        }

        repository.insertQris(qrisModels)
    }

}
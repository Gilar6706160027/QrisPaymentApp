package com.gilaraji.qrispaymentapp.domain.usecase

import com.gilaraji.qrispaymentapp.domain.abstraction.QrisRepository
import com.gilaraji.qrispaymentapp.domain.models.QrisModels

class GetQrisPayment(
    private val repository: QrisRepository
) {
    suspend operator fun invoke(): List<QrisModels> {
        return repository.getAllQris()
    }
}
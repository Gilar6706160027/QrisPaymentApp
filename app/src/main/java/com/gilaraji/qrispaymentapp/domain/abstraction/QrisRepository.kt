package com.gilaraji.qrispaymentapp.domain.abstraction

import com.gilaraji.qrispaymentapp.domain.models.QrisModels

interface QrisRepository {
    suspend fun getAllQris(): List<QrisModels>

    suspend fun insertQris(qrisModels: QrisModels)
}
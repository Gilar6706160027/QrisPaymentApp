package com.gilaraji.qrispaymentapp.data.repository

import com.gilaraji.qrispaymentapp.data.db.dao.UserDao
import com.gilaraji.qrispaymentapp.domain.abstraction.QrisRepository
import com.gilaraji.qrispaymentapp.domain.models.QrisModels
import javax.inject.Inject

class QrisRepositoryImpl @Inject constructor(
    private val dao: UserDao) : QrisRepository {


    override suspend fun getAllQris(): List<QrisModels> {
        return dao.getPaymentQris()
    }

    override suspend fun insertQris(qrisModels: QrisModels) {
        dao.getInsertQris(qrisModels)
    }
}
package com.gilaraji.qrispaymentapp.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.gilaraji.qrispaymentapp.domain.models.QrisModels

@Dao
interface UserDao {

    @Query("SELECT * FROM qrismodels")
    suspend fun getPaymentQris(): List<QrisModels>

    @Insert
    suspend fun getInsertQris(dataList: QrisModels)

}
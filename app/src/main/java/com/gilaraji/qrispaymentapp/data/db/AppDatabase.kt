package com.gilaraji.qrispaymentapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gilaraji.qrispaymentapp.data.db.dao.UserDao
import com.gilaraji.qrispaymentapp.domain.models.QrisModels

@Database(entities = [QrisModels::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
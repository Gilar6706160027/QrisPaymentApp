package com.gilaraji.qrispaymentapp.data.di

import android.content.Context
import androidx.room.Room
import com.gilaraji.qrispaymentapp.data.db.AppDatabase
import com.gilaraji.qrispaymentapp.data.db.dao.UserDao
import com.gilaraji.qrispaymentapp.data.repository.QrisRepositoryImpl
import com.gilaraji.qrispaymentapp.domain.abstraction.QrisRepository
import com.gilaraji.qrispaymentapp.domain.usecase.AddQrisPayment
import com.gilaraji.qrispaymentapp.domain.usecase.GetQrisPayment
import com.gilaraji.qrispaymentapp.domain.usecase.UsecaseQrisPayment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "my-database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserDao(database: AppDatabase): UserDao {
        return database.userDao()
    }


    @Provides
    @Singleton
    fun provideQrisRepository(db: AppDatabase): QrisRepository {
        return QrisRepositoryImpl(db.userDao())
    }

    @Provides
    @Singleton
    fun provideUseCaseQrisPayment(repository: QrisRepository): UsecaseQrisPayment {
        return UsecaseQrisPayment(
            getQrisPayment = GetQrisPayment(repository),
            addQrisPayment = AddQrisPayment(repository)
        )
    }
}

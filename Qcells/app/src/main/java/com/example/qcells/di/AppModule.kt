package com.example.qcells.di

import android.content.Context
import com.example.qcells.installing.InstallService
import com.example.qcells.repository.ApplicationRepository
import com.example.qcells.storage.AppDatabase
import com.example.qcells.storage.ApplicationDao
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
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return AppDatabase.getDatabase(appContext)
    }

    @Provides
    fun provideApplicationRepository(appDatabase: AppDatabase): ApplicationRepository {
        return appDatabase.applicationDao()
    }

    @Provides
    @Singleton
    fun provideInstallService(applicationDao: ApplicationRepository): InstallService {
        return InstallService(applicationDao)
    }

}

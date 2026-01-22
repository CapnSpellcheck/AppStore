package com.example.qcells.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.domain.installing.InstallService
import com.example.domain.installing.StandardInstallService
import com.example.presentation.DummyApplications
import com.example.qcells.repository.ApplicationRepository
import com.example.qcells.storage.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return getDatabase(appContext)
    }

    @Provides
    fun provideApplicationRepository(appDatabase: AppDatabase): ApplicationRepository {
        return appDatabase.applicationDao()
    }

    @Provides
    @Singleton
    fun provideInstallService(applicationDao: ApplicationRepository): InstallService {
        return StandardInstallService(applicationDao)
    }

    @Volatile
    private var INSTANCE: AppDatabase? = null

    fun getDatabase(
        context: Context,
        scope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    ): AppDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "app_database"
            )
                .addCallback(AppDatabaseCallback(scope))
                .build()
            INSTANCE = instance
            instance
        }
    }

    private class AppDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    val applicationDao = database.applicationDao()
                    DummyApplications.applications.forEach {
                        applicationDao.create(it)
                    }
                }
            }
        }
    }
}

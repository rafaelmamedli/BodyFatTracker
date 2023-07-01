package com.rafael.bodyfattracker.data.di

import android.content.Context
import androidx.room.Room
import com.rafael.bodyfattracker.data.repo.BodyFatRepository
import com.rafael.bodyfattracker.data.repo.BodyFatRepositoryImpl
import com.rafael.bodyfattracker.data.room.AppDatabase
import com.rafael.bodyfattracker.data.room.BodyFatDao
import com.rafael.bodyfattracker.viewmodel.ViewModel
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
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "body_fat_database"
        ).build()
    }

    @Provides
    fun provideBodyFatDao(appDatabase: AppDatabase): BodyFatDao {
        return appDatabase.bodyFatDao()
    }

    @Provides
    fun provideBodyFatRepository(personDao: BodyFatDao): BodyFatRepository {
        return BodyFatRepositoryImpl(personDao)
    }

    @Provides
    fun provideBodyFatViewModel(repository: BodyFatRepositoryImpl): ViewModel {
        return ViewModel(repository)
    }
}

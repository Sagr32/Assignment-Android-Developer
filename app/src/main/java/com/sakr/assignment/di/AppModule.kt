package com.sakr.assignment.di

import android.content.Context
import androidx.room.Room
import com.sakr.assignment.data.local.AppDatabase
import com.sakr.assignment.data.local.UsersDao
import com.sakr.assignment.data.remote.ApiInterface
import com.sakr.assignment.data.repository.MainRepository
import com.sakr.assignment.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideGSON(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: GsonConverterFactory): ApiInterface {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(gson)
            .build().create(ApiInterface::class.java)
    }

    fun provideMainRepository(
        retrofitService: ApiInterface,
        usersDao: UsersDao
    ): MainRepository {
        return MainRepository(retrofitService, usersDao)

    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext applicationContext: Context): AppDatabase {
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "AppDatabase"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideArticleDao(appDatabase: AppDatabase): UsersDao {
        return appDatabase.usersDao()
    }


}
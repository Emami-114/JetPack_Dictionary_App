package com.example.jetpack_dictionary.core.di

import android.content.Context
import androidx.room.Room
import com.example.jetpack_dictionary.core.util.Constants
import com.example.jetpack_dictionary.core.util.json.GsonParser
import com.example.jetpack_dictionary.data.domain.repository.WordInfoRepository
import com.example.jetpack_dictionary.data.local.LocalWordInfoDataSource
import com.example.jetpack_dictionary.data.local.dao.WordInfoDao
import com.example.jetpack_dictionary.data.local.db.WordInfoDatabase
import com.example.jetpack_dictionary.data.local.entity.Converters
import com.example.jetpack_dictionary.data.remote.RemoteWordInfoDataSource
import com.example.jetpack_dictionary.data.remote.api.DictionaryApi
import com.example.jetpack_dictionary.data.repository.WordInfoRepositoryImpl
import com.google.gson.Gson

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
object WordInfoModule {

    @Provides
    @Singleton
    fun provideWordInfoDatabase(@ApplicationContext context: Context): WordInfoDatabase =
        Room.databaseBuilder(
            context,
            WordInfoDatabase::class.java,
            Constants.WORD_INFO_DATABASE_NAME
        ).addTypeConverter(Converters(GsonParser(Gson()))).build()

    @Provides
    @Singleton
    fun provideWordInfoDao(db: WordInfoDatabase): WordInfoDao = db.wordInfoDao

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.DICTIONARY_API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideDictionaryApi(retrofit: Retrofit): DictionaryApi =
        retrofit.create(DictionaryApi::class.java)

    @Provides
    @Singleton
    fun provideWordInfoRepository(
        remoteWordInfoDataSource: RemoteWordInfoDataSource,
        localWordInfoDataSource: LocalWordInfoDataSource
    ): WordInfoRepository =
        WordInfoRepositoryImpl(remoteWordInfoDataSource, localWordInfoDataSource)
}
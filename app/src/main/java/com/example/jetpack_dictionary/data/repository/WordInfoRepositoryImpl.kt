package com.example.jetpack_dictionary.data.repository

import com.example.jetpack_dictionary.core.util.Resource
import com.example.jetpack_dictionary.data.domain.model.WordInfo
import com.example.jetpack_dictionary.data.domain.repository.WordInfoRepository
import com.example.jetpack_dictionary.data.local.LocalWordInfoDataSource
import com.example.jetpack_dictionary.data.local.entity.toWordInfo
import com.example.jetpack_dictionary.data.remote.RemoteWordInfoDataSource
import com.example.jetpack_dictionary.data.remote.dto.ErrorResponse
import com.example.jetpack_dictionary.data.remote.dto.toWordInfoEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WordInfoRepositoryImpl @Inject constructor(
    private val remoteWordInfoDataSource: RemoteWordInfoDataSource,
    private val localWordInfoDataSource: LocalWordInfoDataSource
) : WordInfoRepository {

    override fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>> = flow {
        emit(Resource.Loading())

        val wordInfos = localWordInfoDataSource.getWordInfos(word).map { it.toWordInfo() }
        emit(Resource.Loading(wordInfos))

        val wordInfo = wordInfos.firstOrNull { it.word == word }

        try {
            val response = remoteWordInfoDataSource.getWordInfo(word)
            val remoteWordInfos = response.body()
            if (remoteWordInfos != null) {
                localWordInfoDataSource.deleteWordInfos(remoteWordInfos.map { it.word })
                localWordInfoDataSource.insertWordInfos(remoteWordInfos.map { it.toWordInfoEntity() })
            } else {
                val gson = Gson()
                val type = object : TypeToken<ErrorResponse>() {}.type
                val errorResponse: ErrorResponse =
                    gson.fromJson(response.errorBody()?.charStream(), type)
                if (wordInfo == null) {
                    emit(
                        Resource.Error(
                            "Error ${response.code()}. ${errorResponse.message}",
                            wordInfos
                        )
                    )
                }
            }
        } catch (e: Exception) {
            if (wordInfo == null) emit(Resource.Error(e.message.toString(), wordInfos))
        }

        val newWordInfos = localWordInfoDataSource.getWordInfos(word).map { it.toWordInfo() }
        emit(Resource.Success(newWordInfos))
    }
}
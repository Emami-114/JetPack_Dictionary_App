package com.example.jetpack_dictionary.data.domain.repository


import com.example.jetpack_dictionary.core.util.Resource
import com.example.jetpack_dictionary.data.domain.model.WordInfo
import kotlinx.coroutines.flow.Flow

interface WordInfoRepository {

    fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>>
}
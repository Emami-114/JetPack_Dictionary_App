package com.example.jetpack_dictionary.data.domain.useCase

import com.example.jetpack_dictionary.core.util.Resource
import com.example.jetpack_dictionary.data.domain.model.WordInfo
import com.example.jetpack_dictionary.data.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetWordInfoUseCase @Inject constructor(private val repository: WordInfoRepository) {

    operator fun invoke(word: String): Flow<Resource<List<WordInfo>>> {
        if (word.isBlank()) {
            return flow {}
        }
        return repository.getWordInfo(word)
    }
}
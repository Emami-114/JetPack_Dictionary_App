package com.example.jetpack_dictionary.data.remote.dto

import com.example.jetpack_dictionary.data.local.entity.WordInfoEntity


data class WordInfoDto(
    val meanings: List<MeaningDto>,
    val phonetic: String?,
    val phonetics: List<PhoneticDto>,
    val word: String
)

fun WordInfoDto.toWordInfoEntity() = WordInfoEntity(
    meanings = meanings.map { it.toMeaning() },
    phonetic = phonetic,
    word = word
)
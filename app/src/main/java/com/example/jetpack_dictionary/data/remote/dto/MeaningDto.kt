package com.example.jetpack_dictionary.data.remote.dto

import com.example.jetpack_dictionary.data.domain.model.Meaning

data class MeaningDto(
    val definitions: List<DefinitionDto>,
    val partOfSpeech: String
)

fun MeaningDto.toMeaning() = Meaning(
    definitions = definitions.map { it.toDefinition() },
    partOfSpeech = partOfSpeech
)
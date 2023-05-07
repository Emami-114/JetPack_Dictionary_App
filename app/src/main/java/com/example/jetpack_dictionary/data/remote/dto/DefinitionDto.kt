package com.example.jetpack_dictionary.data.remote.dto

import com.example.jetpack_dictionary.data.domain.model.Definition


data class DefinitionDto(
    val antonyms: List<String>,
    val definition: String,
    val example: String?,
    val synonyms: List<String>
)

fun DefinitionDto.toDefinition() = Definition(
    antonyms = antonyms,
    definition = definition,
    example = example,
    synonyms = synonyms
)
package com.example.jetpack_dictionary.wordinfo

import com.example.jetpack_dictionary.data.domain.model.WordInfo


data class WordInfoState(
    val wordInfoItems: List<WordInfo> = emptyList(),
    val isLoading: Boolean = false
)
package com.example.jetpack_dictionary.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.jetpack_dictionary.data.domain.model.Meaning
import com.example.jetpack_dictionary.data.domain.model.WordInfo


@Entity
data class WordInfoEntity(
    val meanings: List<Meaning>,
    val phonetic: String?,
    val word: String,
    @PrimaryKey val id: Int? = null
)

fun WordInfoEntity.toWordInfo() = WordInfo(
    meanings = meanings,
    phonetic = phonetic,
    word = word
)
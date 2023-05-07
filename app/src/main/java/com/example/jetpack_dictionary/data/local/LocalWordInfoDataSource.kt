package com.example.jetpack_dictionary.data.local


import com.example.jetpack_dictionary.data.local.dao.WordInfoDao
import com.example.jetpack_dictionary.data.local.entity.WordInfoEntity
import javax.inject.Inject

class LocalWordInfoDataSource @Inject constructor(private val wordInfoDao: WordInfoDao) {

    suspend fun insertWordInfos(wordInfos: List<WordInfoEntity>) {
        wordInfoDao.insertWordInfos(wordInfos)
    }

    suspend fun deleteWordInfos(words: List<String>) {
        wordInfoDao.deleteWordInfos(words)
    }

    suspend fun getWordInfos(word: String): List<WordInfoEntity> {
        return wordInfoDao.getWordInfos(word)
    }
}
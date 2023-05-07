package com.example.jetpack_dictionary.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.jetpack_dictionary.data.local.dao.WordInfoDao
import com.example.jetpack_dictionary.data.local.entity.Converters
import com.example.jetpack_dictionary.data.local.entity.WordInfoEntity


@Database(
    entities = [WordInfoEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class WordInfoDatabase : RoomDatabase() {

    abstract val wordInfoDao: WordInfoDao
}
package com.rafael.bodyfattracker.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rafael.bodyfattracker.data.model.BodyFatModel

@Database(entities = [BodyFatModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bodyFatDao(): BodyFatDao

}

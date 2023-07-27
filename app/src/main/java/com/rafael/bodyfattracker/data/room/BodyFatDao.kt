package com.rafael.bodyfattracker.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

import com.rafael.bodyfattracker.data.model.BodyFatModel

@Dao
interface BodyFatDao {

    @Insert
    suspend fun insert(bodyFat: BodyFatModel)


    @Delete
    suspend fun delete(bodyFat: BodyFatModel)


    @Update
    suspend fun update(bodyFat: BodyFatModel)

    @Query("SELECT * FROM results")
     fun getAllResults(): List<BodyFatModel>
}


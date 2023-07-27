package com.rafael.bodyfattracker.data.repo

import androidx.lifecycle.LiveData
import com.rafael.bodyfattracker.data.model.BodyFatModel

interface BodyFatRepository  {
    suspend fun getAllResults(): List<BodyFatModel>
    suspend fun insert(bodyFat: BodyFatModel)

    suspend fun update(bodyFat: BodyFatModel)

    suspend fun delete(bodyFat: BodyFatModel)
}

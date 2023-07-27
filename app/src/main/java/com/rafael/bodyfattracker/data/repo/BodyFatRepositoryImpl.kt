package com.rafael.bodyfattracker.data.repo

import androidx.lifecycle.LiveData
import com.rafael.bodyfattracker.data.room.BodyFatDao
import com.rafael.bodyfattracker.data.model.BodyFatModel
import javax.inject.Inject

class BodyFatRepositoryImpl @Inject constructor(private val bodyFatDao: BodyFatDao): BodyFatRepository {

    override suspend fun getAllResults(): List<BodyFatModel> {
        return bodyFatDao.getAllResults()
    }

    override suspend fun insert(bodyFat: BodyFatModel) {
        bodyFatDao.insert(bodyFat)
    }
    override suspend fun update(bodyFat: BodyFatModel) {
        bodyFatDao.update(bodyFat)
    }

    override suspend fun delete(bodyFat: BodyFatModel) {
        bodyFatDao.delete(bodyFat)
    }


}



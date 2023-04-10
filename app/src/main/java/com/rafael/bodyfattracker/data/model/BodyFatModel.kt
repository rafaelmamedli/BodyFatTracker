package com.rafael.bodyfattracker.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "results")
data class BodyFatModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val value: String,
    val date: String
)

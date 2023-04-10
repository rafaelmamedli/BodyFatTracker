package com.rafael.bodyfattracker.viewmodel

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafael.bodyfattracker.data.repo.BodyFatRepositoryImpl
import com.rafael.bodyfattracker.data.model.BodyFatModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log10

@HiltViewModel
class ViewModel @Inject constructor(private val repository: BodyFatRepositoryImpl) : ViewModel() {
    var result = MutableLiveData<String>()
    var pro :Double = 0.00

    fun getAllResults(): LiveData<List<BodyFatModel>> {
        return repository.getAllResults()
    }

    fun insert(bodyFat: BodyFatModel) = viewModelScope.launch {
        repository.insert(bodyFat)
    }


    fun update(bodyFat: BodyFatModel) = viewModelScope.launch {
        repository.update(bodyFat)
    }



    fun delete(bodyFat: BodyFatModel) = viewModelScope.launch {
        repository.delete(bodyFat)
    }


    fun bodyFatProCalculator(
        weight_value: Editable?,
        height_value: Editable?,
        neck_value: Editable?,
        abdomen_value: Editable?,
        gender: Boolean?,
        hip_value: Editable?
    ) {

        if (!weight_value.isNullOrEmpty() && !height_value.isNullOrEmpty() && !neck_value.isNullOrEmpty() && !abdomen_value.isNullOrEmpty()) {
            val heightValue = height_value.toString().toDouble()
            val neckValue = neck_value.toString().toDouble()
            val abdomenValue = abdomen_value.toString().toDouble()
            val gender = gender

            if (gender == true) {
                pro = (82.3 * log10(abdomenValue - neckValue) - 70.041 * log10(heightValue) + 36.76)
            } else {
                if (hip_value != null && hip_value.isNotEmpty()){
                    val hipValue = hip_value.toString().toDouble()
                    pro = (163.205 * log10(abdomenValue + hipValue - neckValue) - 97.684 * log10(heightValue) - 78.387)
                } else {
                    pro  = 0.00

                }
            }

            result.value = "$pro %"

        }
    }
}




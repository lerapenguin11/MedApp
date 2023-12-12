package com.example.medapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.common.ResultMed
import com.example.domain.entity.AddPatient
import com.example.domain.usecase.GetAddPatientUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddPatientViewModel(
    private val getAddPatientUseCase: GetAddPatientUseCase
) : ViewModel()
{

    fun addPatient(patient: AddPatient) {
        viewModelScope.launch(Dispatchers.Default) {
            when (val response = getAddPatientUseCase.invoke(patient)) {
                is ResultMed.Success<*> -> {
                    Log.d("ADD PATIENT: ", response.data.toString())
                }

                is ResultMed.Error -> {
                    Log.e("ADD PATIENT ERROR: ", response.exception.toString())
                    println("ADD PATIENT ERROR: ${response.exception}")
                }
            }
        }
    }
}
package com.example.medapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.common.ResultMed
import com.example.domain.entity.patient.AddPatient
import com.example.domain.entity.patient.NewPatientId
import com.example.domain.usecase.patient.GetAddPatientUseCase
import kotlinx.coroutines.launch

class AddPatientViewModel(
    private val getAddPatientUseCase: GetAddPatientUseCase
) : ViewModel()
{
    private val _patient = MutableLiveData<NewPatientId?>()
    val patientId: LiveData<NewPatientId?> get() = _patient

    private val _errorPatient = MutableLiveData<String>()
    val errorPatient: LiveData<String> = _errorPatient

    fun addPatient(patient: AddPatient) {
        viewModelScope.launch {
            when (val response = getAddPatientUseCase.invoke(patient)) {
                is ResultMed.Success -> {
                    _patient.value = response.data
                }

                is ResultMed.Error -> {
                    _errorPatient.value = response.exception.message
                }
            }
        }
    }
}
package com.example.medapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.common.ResultMed
import com.example.domain.entity.patient.NewPatientId
import com.example.domain.usecase.patient.GetPatientIdUseCase
import kotlinx.coroutines.launch

class ChangeInformationViewModel(
    private val getPatientIdUseCase: GetPatientIdUseCase
) : ViewModel()
{
    private val _patient = MutableLiveData<NewPatientId?>()
    val patientId: LiveData<NewPatientId?> get() = _patient

    private val _errorPatient = MutableLiveData<String>()
    val errorPatient: LiveData<String> = _errorPatient

    fun getPatientId(id : String) {
        viewModelScope.launch {
            when (val patientIdResult = getPatientIdUseCase.invoke(id = id)) {
                is ResultMed.Success -> {
                    _patient.value = patientIdResult.data
                }

                is ResultMed.Error -> {
                    _errorPatient.postValue(patientIdResult.exception.message)
                }
            }
        }
    }
}
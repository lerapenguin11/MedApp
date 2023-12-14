package com.example.medapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.common.ResultMed
import com.example.domain.entity.patient.Patients
import com.example.domain.usecase.patient.GetPatientListUseCase
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getPatientListUseCase: GetPatientListUseCase
)
    : ViewModel()
{
    private val _patient = MutableLiveData<List<Patients>?>()
    val patients: LiveData<List<Patients>?> get() = _patient

    private val _errorPatient = MutableLiveData<String>()
    val errorPatient: LiveData<String> = _errorPatient

    fun getPatient() {
        viewModelScope.launch {
            when (val patientResult = getPatientListUseCase.invoke()) {
                is ResultMed.Success -> {
                    _patient.value = patientResult.data
                }

                is ResultMed.Error -> {
                    _errorPatient.postValue(patientResult.exception.message)
                }
            }
        }
    }
}
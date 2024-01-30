package com.example.home_presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common_utils.common.ResultMed
import com.example.home_domain.entity.Patients
import com.example.home_domain.usecase.GetPatientListUseCase
import dagger.Provides
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPatientListUseCase : GetPatientListUseCase)
    : ViewModel()
{
    private val _patient = MutableLiveData<List<Patients>?>()
    val patients: LiveData<List<Patients>?> get() = _patient

    private val _errorPatient = MutableLiveData<String>()
    val errorPatient: LiveData<String> = _errorPatient

    init {
        getPatient()
    }

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
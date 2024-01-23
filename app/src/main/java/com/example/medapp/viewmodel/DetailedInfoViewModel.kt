package com.example.medapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.common.ResultMed
import com.example.domain.entity.analysis.AnalysisList
import com.example.domain.entity.patient.NewPatientId
import com.example.domain.usecase.analysis.GetAnalysisListUseCase
import com.example.domain.usecase.patient.GetPatientIdUseCase
import kotlinx.coroutines.launch

class DetailedInfoViewModel(
    private val getPatientIdUseCase: GetPatientIdUseCase,
    private val getAnalysisListUseCase: GetAnalysisListUseCase
) : ViewModel()
{
    private val _patient = MutableLiveData<NewPatientId?>()
    val patientId: LiveData<NewPatientId?> get() = _patient

    private val _errorPatient = MutableLiveData<String>()
    val errorPatient: LiveData<String> = _errorPatient

    private val _analysisList = MutableLiveData<List<AnalysisList>?>()
    val analysisList: MutableLiveData<List<AnalysisList>?> get() = _analysisList

    private val _errorAnalysisList = MutableLiveData<String>()
    val errorAnalysisList: LiveData<String> = _errorAnalysisList

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

    fun getAnalysisList(idPatient : String) {
        viewModelScope.launch {
            when (val analysisResult = getAnalysisListUseCase.invoke(idPatient = idPatient)) {
                is ResultMed.Success -> {
                    _analysisList.value = analysisResult.data
                }

                is ResultMed.Error -> {
                    _errorAnalysisList.postValue(analysisResult.exception.message)
                }
            }
        }
    }
}
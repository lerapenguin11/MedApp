package com.example.medapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.common.ResultMed
import com.example.domain.entity.analysis.patientAnalysis.PatientAnalysisList
import com.example.domain.usecase.analysis.GetPatientAnalysisListUseCase
import kotlinx.coroutines.launch

class AnalysisViewModel(
    private val getPatientAnalysisListUseCase : GetPatientAnalysisListUseCase
) : ViewModel()
{
    private val _analysisList = MutableLiveData<List<PatientAnalysisList>?>()
    val analysisList: LiveData<List<PatientAnalysisList>?> get() = _analysisList

    private val _errorAnalysisList = MutableLiveData<String>()
    val errorAnalysisList: LiveData<String> = _errorAnalysisList

    fun getAnalysisList(idPatient : String) {
        viewModelScope.launch {
            when (val analysisResult = getPatientAnalysisListUseCase.invoke(idPatient = idPatient)) {
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
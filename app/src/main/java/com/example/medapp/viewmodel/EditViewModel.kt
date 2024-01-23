package com.example.medapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.api.model.analysis.CytokineStatus
import com.example.data.api.model.analysis.PatientAnalysisListModelItem
import com.example.domain.common.ResultMed
import com.example.domain.entity.analysis.HematologicalStatus
import com.example.domain.entity.analysis.patientAnalysis.PatientAnalysisList
import com.example.domain.usecase.analysis.GetAnalysisIdUseCase
import com.example.domain.usecase.analysis.GetPatientAnalysisListUseCase
import com.example.domain.usecase.analysis.GetUpdateCytokineStatusUseCase
import com.example.domain.usecase.analysis.GetUpdateHematologicalStatusUseCase
import com.example.domain.usecase.analysis.GetUpdateImmuneStatusUseCase
import kotlinx.coroutines.launch

class EditViewModel(
    private val getAnalysisIdUseCase : GetAnalysisIdUseCase,
    private val getUpdateImmuneStatusUseCase: GetUpdateImmuneStatusUseCase,
    private val getUpdateHematologicalStatusUseCase: GetUpdateHematologicalStatusUseCase,
    private val getUpdateCytokineStatusUseCase: GetUpdateCytokineStatusUseCase
) : ViewModel()
{
    private val _analysisList = MutableLiveData<PatientAnalysisList?>()
    val analysisList: LiveData<PatientAnalysisList?> get() = _analysisList
    private val _errorAnalysisList = MutableLiveData<String>()
    val errorAnalysisList: LiveData<String> = _errorAnalysisList

    private val _hematological = MutableLiveData<HematologicalStatus?>()

    fun getAnalysisList(analysisId : String) {
        viewModelScope.launch {
            when (val analysisResult =
                getAnalysisIdUseCase.invoke(idAnalysis = analysisId)) {
                is ResultMed.Success -> {
                    _analysisList.value = analysisResult.data
                }

                is ResultMed.Error -> {
                    _errorAnalysisList.postValue(analysisResult.exception.message)
                }
            }
        }
    }

    fun getUpdateCytokineStatus(analysisId : String, status : com.example.domain.entity.analysis.CytokineStatus) {
        viewModelScope.launch {
            when (val analysisResult =
                getUpdateCytokineStatusUseCase.invoke(idAnalysis = analysisId, status = status)) {
                is ResultMed.Success -> {

                }

                is ResultMed.Error -> {
                    _errorAnalysisList.postValue(analysisResult.exception.message)
                }
            }
        }
    }

    fun getUpdateHematologicalStatus(analysisId : String, status : com.example.domain.entity.analysis.HematologicalStatus) {
        viewModelScope.launch {
            when (val analysisResult =
                getUpdateHematologicalStatusUseCase.invoke(idAnalysis = analysisId, status = status)) {
                is ResultMed.Success -> {
                    _hematological.value = analysisResult.data
                }

                is ResultMed.Error -> {
                    _errorAnalysisList.postValue(analysisResult.exception.message)
                }
            }
        }
    }

    fun getUpdateImmuneStatus(analysisId : String, status : com.example.domain.entity.analysis.ImmuneStatus) {
        viewModelScope.launch {
            when (val analysisResult =
                getUpdateImmuneStatusUseCase.invoke(idAnalysis = analysisId, status = status)) {
                is ResultMed.Success -> {

                }

                is ResultMed.Error -> {
                    _errorAnalysisList.postValue(analysisResult.exception.message)
                }
            }
        }
    }



}
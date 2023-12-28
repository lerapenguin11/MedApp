package com.example.medapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.common.ResultMed
import com.example.domain.entity.analysis.Analysis
import com.example.domain.entity.analysis.HematologicalStatus
import com.example.domain.entity.analysis.ImmuneStatus
import com.example.domain.usecase.analysis.GetAddAnalysisUseCase
import com.example.domain.usecase.analysis.GetAddHematologicalStatusUseCase
import com.example.domain.usecase.analysis.GetAddImmuneStatusUseCase
import com.example.domain.usecase.analysis.GetUpdateAnalysisDateUseCase
import kotlinx.coroutines.launch

class AddAnalysisViewModel(
    private val getAddAnalysisUseCase: GetAddAnalysisUseCase,
    private val getAddHematologicalStatusUseCase: GetAddHematologicalStatusUseCase,
    private val getUpdateAnalysisDateUseCase: GetUpdateAnalysisDateUseCase,
    private val getAddImmuneStatusUseCase: GetAddImmuneStatusUseCase
) : ViewModel()
{
    private val _analysis = MutableLiveData<Analysis?>()
    val analysis: LiveData<Analysis?> get() = _analysis

    private val _errorAnalysis = MutableLiveData<String>()
    val errorAnalysis: LiveData<String> = _errorAnalysis

    fun getAddAnalysis(patientId: String) {
        viewModelScope.launch {
            when (val response = getAddAnalysisUseCase.invoke(idPatient = patientId)) {
                is ResultMed.Success -> {
                    _analysis.value = response.data
                }
                is ResultMed.Error -> {
                    _errorAnalysis.value = response.exception.message
                }
            }
        }
    }

    fun getAddHematologicalStatus(
        patientId: String,
        analysisId : String,
        status : HematologicalStatus) {
        viewModelScope.launch {
            when (val response = getAddHematologicalStatusUseCase.invoke(idPatient = patientId,
                idAnalysis = analysisId, status = status)) {
                is ResultMed.Success -> {
                    Log.d("AddHematologicalStatus: ", response.data.toString())
                }
                is ResultMed.Error -> {
                    Log.d("AddHematologicalStatusError: ",
                        response.exception.message.toString())
                }
            }
        }
    }

    fun getImmuneStatus(
        patientId: String,
        analysisId : String,
        status : ImmuneStatus) {
        viewModelScope.launch {
            when (val response = getAddImmuneStatusUseCase.invoke(idPatient = patientId,
                idAnalysis = analysisId, status = status)) {
                is ResultMed.Success -> {
                    Log.d("AddHematologicalStatus: ", response.data.toString())
                }
                is ResultMed.Error -> {
                    Log.d("AddHematologicalStatusError: ",
                        response.exception.message.toString())
                }
            }
        }
    }

    fun getUpdateAnalysisDate(
        date: Analysis, idPatient: String,
        idAnalysis: String){
        viewModelScope.launch {
            when (val response = getUpdateAnalysisDateUseCase.invoke(date = date,
                                    idAnalysis = idAnalysis, idPatient = idPatient)) {
                is ResultMed.Success -> {
                    Log.d("UpdateAnalysisDate: ", response.data.toString())
                }
                is ResultMed.Error -> {
                    Log.d("UpdateAnalysisDateError: ",
                        response.exception.message.toString())
                }
            }
        }
    }
}
package com.example.medapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.common.ResultMed
import com.example.domain.entity.analysis.Analysis
import com.example.domain.entity.analysis.CytokineStatus
import com.example.domain.entity.analysis.HematologicalStatus
import com.example.domain.entity.analysis.ImmuneStatus
import com.example.domain.entity.analysis.StatusList
import com.example.domain.usecase.analysis.GetAddAnalysisUseCase
import com.example.domain.usecase.analysis.GetAddCytokineStatusUseCase
import com.example.domain.usecase.analysis.GetAddHematologicalStatusUseCase
import com.example.domain.usecase.analysis.GetAddImmuneStatusUseCase
import com.example.domain.usecase.analysis.GetUpdateAnalysisDateUseCase
import com.example.domain.usecase.analysis.GetValuesCytokineStatusUseCase
import com.example.domain.usecase.analysis.GetValuesHematologicalStatusUseCase
import com.example.domain.usecase.analysis.GetValuesImmuneStatusUseCase
import kotlinx.coroutines.launch

class AddAnalysisViewModel(
    private val getAddAnalysisUseCase: GetAddAnalysisUseCase,
    private val getAddHematologicalStatusUseCase: GetAddHematologicalStatusUseCase,
    private val getUpdateAnalysisDateUseCase: GetUpdateAnalysisDateUseCase,
    private val getAddImmuneStatusUseCase: GetAddImmuneStatusUseCase,
    private val getAddCytokineStatusUseCase: GetAddCytokineStatusUseCase,
    private val getValuesHematologicalStatusUseCase: GetValuesHematologicalStatusUseCase,
    private val getValuesImmuneStatusUseCase: GetValuesImmuneStatusUseCase,
    private val getValuesCytokineStatusUseCase: GetValuesCytokineStatusUseCase
) : ViewModel()
{
    private val _analysis = MutableLiveData<Analysis?>()
    val analysis: LiveData<Analysis?> get() = _analysis

    private val _errorAnalysis = MutableLiveData<String>()
    val errorAnalysis: LiveData<String> = _errorAnalysis

    private val _statusHematological = MutableLiveData<List<StatusList>?>()
    val statusHematological: LiveData<List<StatusList>?> get() = _statusHematological

    private val _statusImmune = MutableLiveData<List<StatusList>?>()
    val statusImmune: LiveData<List<StatusList>?> get() = _statusImmune

    private val _statusCytokine = MutableLiveData<List<StatusList>?>()
    val statusCytokine: LiveData<List<StatusList>?> get() = _statusCytokine

    fun getStatusCytokine() {
        viewModelScope.launch {
            when (val response = getValuesCytokineStatusUseCase.invoke()) {
                is ResultMed.Success -> {
                    _statusCytokine.value = response.data
                }
                is ResultMed.Error -> {
                    _errorAnalysis.value = response.exception.message
                }
            }
        }
    }

    fun getStatusImmune() {
        viewModelScope.launch {
            when (val response = getValuesImmuneStatusUseCase.invoke()) {
                is ResultMed.Success -> {
                    _statusImmune.value = response.data
                }
                is ResultMed.Error -> {
                    _errorAnalysis.value = response.exception.message
                }
            }
        }
    }

    fun getStatusHematological() {
        viewModelScope.launch {
            when (val response = getValuesHematologicalStatusUseCase.invoke()) {
                is ResultMed.Success -> {
                    _statusHematological.value = response.data
                }
                is ResultMed.Error -> {
                    _errorAnalysis.value = response.exception.message
                }
            }
        }
    }

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
                    Log.d("AddImmuneStatusStatus: ", response.data.toString())
                }
                is ResultMed.Error -> {
                    Log.d("AddImmuneStatusError: ",
                        response.exception.message.toString())
                }
            }
        }
    }

    fun getCytokineStatus(
        patientId: String,
        analysisId : String,
        status : CytokineStatus) {
        viewModelScope.launch {
            when (val response = getAddCytokineStatusUseCase.invoke(idPatient = patientId,
                idAnalysis = analysisId, status = status)) {
                is ResultMed.Success -> {
                    Log.d("AddCytokinStatus: ", response.data.toString())
                }
                is ResultMed.Error -> {
                    Log.d("AddCytokinError: ",
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
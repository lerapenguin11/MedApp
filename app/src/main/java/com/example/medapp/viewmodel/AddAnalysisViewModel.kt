package com.example.medapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.common.ResultMed
import com.example.domain.entity.Analysis
import com.example.domain.usecase.analysis.GetAddAnalysisUseCase
import kotlinx.coroutines.launch

class AddAnalysisViewModel(
    private val getAddAnalysisUseCase: GetAddAnalysisUseCase
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
}
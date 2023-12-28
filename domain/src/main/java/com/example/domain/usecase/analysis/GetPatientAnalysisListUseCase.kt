package com.example.domain.usecase.analysis

import com.example.domain.repository.AnalysisRepository

class GetPatientAnalysisListUseCase(private val repository : AnalysisRepository)
{
    suspend operator fun invoke(idPatient : String) =
        repository.getPatientAnalysisList(patientId = idPatient)
}
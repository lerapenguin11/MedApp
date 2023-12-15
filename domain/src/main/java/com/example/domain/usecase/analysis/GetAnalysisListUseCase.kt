package com.example.domain.usecase.analysis

import com.example.domain.repository.AnalysisRepository

class GetAnalysisListUseCase(
    private val repository: AnalysisRepository)
{
    suspend operator fun invoke(idPatient : String) =
        repository.getAnalysisList(patientId = idPatient)
}
package com.example.domain.usecase.analysis

import com.example.domain.repository.AddAnalysisRepository

class GetAnalysisListUseCase(
    private val repository: AddAnalysisRepository)
{
    suspend operator fun invoke(idPatient : String) =
        repository.getAnalysisList(patientId = idPatient)
}
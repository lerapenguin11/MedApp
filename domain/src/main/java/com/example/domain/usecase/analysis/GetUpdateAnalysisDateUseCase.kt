package com.example.domain.usecase.analysis

import com.example.domain.entity.analysis.Analysis
import com.example.domain.repository.AddAnalysisRepository

class GetUpdateAnalysisDateUseCase(
    private val repository: AddAnalysisRepository)
{
    suspend operator fun invoke(
        date : Analysis,
        idPatient : String,
        idAnalysis : String) =
        repository.getUpdateAnalysisDate(date = date,
            idPatient = idPatient, idAnalysis = idAnalysis)
}
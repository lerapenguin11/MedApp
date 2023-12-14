package com.example.domain.usecase.analysis

import com.example.domain.entity.analysis.Analysis
import com.example.domain.repository.AnalysisRepository

class GetUpdateAnalysisDateUseCase(
    private val repository: AnalysisRepository)
{
    suspend operator fun invoke(
        date : Analysis,
        idPatient : String,
        idAnalysis : String) =
        repository.getUpdateAnalysisDate(date = date,
            idPatient = idPatient, idAnalysis = idAnalysis)
}
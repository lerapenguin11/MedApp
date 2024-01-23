package com.example.domain.usecase.analysis

import com.example.domain.entity.analysis.CytokineStatus
import com.example.domain.repository.AddAnalysisRepository

class GetAnalysisIdUseCase(private val repository: AddAnalysisRepository)
{
    suspend operator fun invoke(
        idAnalysis : String
    ) =
        repository.getAnalysisId(
            analysisId = idAnalysis)
}
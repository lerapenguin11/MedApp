package com.example.domain.usecase.analysis

import com.example.domain.entity.analysis.CytokineStatus
import com.example.domain.repository.AddAnalysisRepository

class GetUpdateCytokineStatusUseCase(
    private val repository: AddAnalysisRepository
)
{
    suspend operator fun invoke(
        idAnalysis : String, status: CytokineStatus
    ) =
        repository.updateCytokineStatus(
            status = status, idAnalysis = idAnalysis)
}
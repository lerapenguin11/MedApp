package com.example.domain.usecase.analysis

import com.example.domain.entity.analysis.HematologicalStatus
import com.example.domain.repository.AddAnalysisRepository

class GetUpdateHematologicalStatusUseCase(
    private val repository: AddAnalysisRepository
)
{
    suspend operator fun invoke(
        idAnalysis : String, status: HematologicalStatus
    ) =
        repository.updateHematologicalStatus(
            status = status, idAnalysis = idAnalysis)
}
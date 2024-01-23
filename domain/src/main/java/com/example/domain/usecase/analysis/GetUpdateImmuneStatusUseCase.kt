package com.example.domain.usecase.analysis

import com.example.domain.entity.analysis.Analysis
import com.example.domain.entity.analysis.ImmuneStatus
import com.example.domain.repository.AddAnalysisRepository

class GetUpdateImmuneStatusUseCase(
    private val repository: AddAnalysisRepository)
{
    suspend operator fun invoke(
        idAnalysis : String, status: ImmuneStatus) =
        repository.updateImmuneStatus(
            status = status, idAnalysis = idAnalysis)
}
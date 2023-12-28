package com.example.domain.usecase.analysis

import com.example.domain.entity.analysis.HematologicalStatus
import com.example.domain.repository.AddAnalysisRepository

class GetAddHematologicalStatusUseCase(
    private val repository: AddAnalysisRepository)
{
    suspend operator fun invoke(idPatient : String,
                                idAnalysis : String,
                                status : HematologicalStatus) =
        repository.getAddHematologicalStatus(idPatient = idPatient,
            idAnalysis = idAnalysis, status = status)
}
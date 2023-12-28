package com.example.domain.usecase.analysis

import com.example.domain.entity.analysis.ImmuneStatus
import com.example.domain.repository.AnalysisRepository

class GetAddImmuneStatusUseCase(private val repository: AnalysisRepository)
{
    suspend operator fun invoke(idPatient : String,
                                idAnalysis : String,
                                status : ImmuneStatus
    ) =
        repository.getAddImmuneStatus(idPatient = idPatient,
            idAnalysis = idAnalysis, status = status)
}
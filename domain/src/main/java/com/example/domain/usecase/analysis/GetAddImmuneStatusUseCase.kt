package com.example.domain.usecase.analysis

import com.example.domain.entity.analysis.ImmuneStatus
import com.example.domain.repository.AddAnalysisRepository

class GetAddImmuneStatusUseCase(private val repository: AddAnalysisRepository)
{
    suspend operator fun invoke(idPatient : String,
                                idAnalysis : String,
                                status : ImmuneStatus
    ) =
        repository.getAddImmuneStatus(idPatient = idPatient,
            idAnalysis = idAnalysis, status = status)
}
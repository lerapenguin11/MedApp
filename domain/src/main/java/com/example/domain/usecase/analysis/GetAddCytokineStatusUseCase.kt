package com.example.domain.usecase.analysis

import com.example.domain.entity.analysis.CytokineStatus
import com.example.domain.repository.AnalysisRepository

class GetAddCytokineStatusUseCase(private val repository: AnalysisRepository)
{
    suspend operator fun invoke(idPatient : String,
                                idAnalysis : String,
                                status : CytokineStatus
    ) =
        repository.getAddCytokineStatus(idPatient = idPatient,
            idAnalysis = idAnalysis, status = status)
}
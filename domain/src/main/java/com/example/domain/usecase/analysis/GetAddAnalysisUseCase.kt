package com.example.domain.usecase.analysis

import com.example.domain.repository.AnalysisRepository

class GetAddAnalysisUseCase(private val repository: AnalysisRepository) {

    suspend operator fun invoke(idPatient : String) =
        repository.getNewAnalysis(idPatient = idPatient )
}
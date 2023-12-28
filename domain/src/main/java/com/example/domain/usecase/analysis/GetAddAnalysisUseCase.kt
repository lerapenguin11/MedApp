package com.example.domain.usecase.analysis

import com.example.domain.repository.AddAnalysisRepository

class GetAddAnalysisUseCase(private val repository: AddAnalysisRepository) {

    suspend operator fun invoke(idPatient : String) =
        repository.getNewAnalysis(idPatient = idPatient )
}
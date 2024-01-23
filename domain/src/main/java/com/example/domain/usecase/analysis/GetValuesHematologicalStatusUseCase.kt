package com.example.domain.usecase.analysis

import com.example.domain.repository.AddAnalysisRepository

class GetValuesHematologicalStatusUseCase(
    private val repository: AddAnalysisRepository)
{
    suspend operator fun invoke() =
        repository.getValuesHematologicalStatus()
}
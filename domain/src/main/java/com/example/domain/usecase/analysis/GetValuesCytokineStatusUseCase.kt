package com.example.domain.usecase.analysis

import com.example.domain.repository.AddAnalysisRepository

class GetValuesCytokineStatusUseCase(private val repository: AddAnalysisRepository)
{
    suspend operator fun invoke() =
        repository.getValuesCytokineStatus()
}
package com.example.domain.usecase.analysis

import com.example.domain.repository.AddAnalysisRepository

class GetValuesImmuneStatusUseCase(private val repository: AddAnalysisRepository)
{
    suspend operator fun invoke() =
        repository.getValuesImmuneStatus()
}
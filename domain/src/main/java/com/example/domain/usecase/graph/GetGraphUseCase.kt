package com.example.domain.usecase.graph

import com.example.domain.repository.GraphRepository

class GetGraphUseCase(private val repository: GraphRepository)
{
    suspend operator fun invoke(analysisId : String) =
        repository.getGraph(analysisId = analysisId)
}
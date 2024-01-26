package com.example.domain.usecase.graph

import com.example.domain.repository.GraphRepository

class GetGraphTCellUseCase(private val repository: GraphRepository)
{
    suspend operator fun invoke(analysisId : String) =
        repository.getGraphTCell(analysisId = analysisId)
}
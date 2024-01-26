package com.example.domain.usecase.graph

import com.example.domain.repository.GraphRepository

class GetGraphBCellUseCase(private val repository: GraphRepository)
{
    suspend operator fun invoke(analysisId : String) =
        repository.getGraphBCell(analysisId = analysisId)
}
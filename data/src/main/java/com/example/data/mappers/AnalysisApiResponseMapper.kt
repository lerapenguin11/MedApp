package com.example.data.mappers

import com.example.data.api.model.analysis.AnalysisModel
import com.example.domain.entity.Analysis

class AnalysisApiResponseMapper {

    fun toAnalysisModel(response : AnalysisModel) : Analysis{
        return Analysis(
            id = response.id,
            patientId = response.patientId,
            cytokineStatusId = response.cytokineStatusId,
            hematologicalStatusId = response.hematologicalStatusId,
            immuneStatusId = response.immuneStatusId
        )
    }
}
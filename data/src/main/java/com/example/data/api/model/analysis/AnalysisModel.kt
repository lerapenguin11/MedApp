package com.example.data.api.model.analysis

data class AnalysisModel(
    val id: Int,
    val patientId: Int,
    var executionDateStr: String?,
    val cytokineStatusId: Int?,
    val hematologicalStatusId: Int?,
    val immuneStatusId: Int?
)

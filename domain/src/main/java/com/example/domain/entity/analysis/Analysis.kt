package com.example.domain.entity.analysis

data class Analysis(
    val id: Int,
    val patientId: Int,
    val executionDateStr: String?,
    val cytokineStatusId: Int?,
    val hematologicalStatusId: Int?,
    val immuneStatusId: Int?
)

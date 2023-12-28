package com.example.domain.entity.analysis.patientAnalysis

data class PatientAnalysisList(
    val createdAt: String,
    val cytokineStatus: Cytokine,
    val cytokineStatusId: Int,
    val deletedAt: String?,
    val executionDateStr: String,
    val hematologicalStatus: Hematological,
    val hematologicalStatusId: Int,
    val id: Int,
    val immuneStatus: Immune,
    val immuneStatusId: Int,
    val patientId: Int,
    val updatedAt: String?
)

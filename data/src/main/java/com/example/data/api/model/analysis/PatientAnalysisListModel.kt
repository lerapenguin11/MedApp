package com.example.data.api.model.analysis
//TODO переписать
class PatientAnalysisListModel : ArrayList<PatientAnalysisListModelItem>()

data class PatientAnalysisListModelItem(
    val createdAt: String,
    val cytokineStatus: CytokineStatus,
    val cytokineStatusId: Int,
    val deletedAt: String?,
    val executionDateStr: String,
    val hematologicalStatus: HematologicalStatus,
    val hematologicalStatusId: Int,
    val id: Int,
    val immuneStatus: ImmuneStatus,
    val immuneStatusId: Int,
    val patientId: Int,
    val updatedAt: String?
)
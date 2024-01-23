package com.example.data.api.model.analysis

import com.example.domain.entity.analysis.patientAnalysis.Cytokine
import com.example.domain.entity.analysis.patientAnalysis.Hematological
import com.example.domain.entity.analysis.patientAnalysis.Immune

//TODO переписать
class PatientAnalysisListModel : ArrayList<PatientAnalysisListModelItem>()

data class PatientAnalysisListModelItem(
    val createdAt: String,
    val cytokineStatus: Cytokine?,
    val cytokineStatusId: Int?,
    val deletedAt: String?,
    val executionDateStr: String?,
    val hematologicalStatus: Hematological?,
    val hematologicalStatusId: Int?,
    val id: Int,
    val immuneStatus: Immune?,
    val immuneStatusId: Int?,
    val patientId: Int,
    val updatedAt: String?
)
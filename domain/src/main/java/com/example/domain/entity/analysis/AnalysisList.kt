package com.example.domain.entity.analysis

//class AnalysisList : ArrayList<AnalysisListItem>()

data class AnalysisList(
    val executionDateStr: String,
    val id: Int,
    val patientId: Int,
)
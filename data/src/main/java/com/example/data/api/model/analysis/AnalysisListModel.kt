package com.example.data.api.model.analysis

class AnalysisListModel : ArrayList<AnalysisListModelItem>()

data class AnalysisListModelItem(
    val executionDateStr: String,
    val id: Int,
    val patientId: Int,
)
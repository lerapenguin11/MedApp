package com.example.domain.entity

data class Analysis(
    val id: Int,
    val patientId: Int,
    val cytokineStatusId: Int,
    val hematologicalStatusId: Int,
    val immuneStatusId: Int
)

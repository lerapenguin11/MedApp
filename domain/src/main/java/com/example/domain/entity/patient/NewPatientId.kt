package com.example.domain.entity.patient

data class NewPatientId(
    val birthDateStr : String,
    val createdAt: String,
    val deletedAt: String?,
    val diagnosis: String?,
    val docNumber: String?,
    val docSeries: String?,
    val firstName: String,
    val id: Int,
    val lastName: String,
    val middleName: String?,
    val updatedAt: String
)
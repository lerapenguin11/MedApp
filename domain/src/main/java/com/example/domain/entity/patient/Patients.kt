package com.example.domain.entity.patient

data class Patients(
    val birthDateStr : String?,
    val createdAt: String,
    val deletedAt: Int?,
    val diagnosis: String?,
    val docNumber: Int?,
    val docSeries: Int?,
    val firstName: String,
    val id: Int,
    val lastName: String,
    val middleName: String?,
    val updatedAt: String
)
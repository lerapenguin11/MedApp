package com.example.domain.entity.patient

data class NewPatientId(
    val age: Any?,
    val createdAt: String,
    val deletedAt: Any?,
    val diagnosis: String?,
    val docNumber: Any?,
    val docSeries: Int?,
    val firstName: String,
    val id: Int,
    val lastName: String,
    val middleName: String?,
    val updatedAt: String
)
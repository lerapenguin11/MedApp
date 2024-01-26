package com.example.home_domain.entity

data class Patients(
    val birthDateStr : String?,
    val createdAt: String,
    val deletedAt: Int?,
    val diagnosis: String?,
    val docNumber: String?,
    val docSeries: String?,
    val firstName: String,
    val id: Int,
    val lastName: String,
    val middleName: String?,
    val updatedAt: String
)

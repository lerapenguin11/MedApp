package com.example.domain.entity.patient

data class AddPatient(
    val birthDateStr : String,
    val diagnosis: String?,
    val docNumber: String?,
    val docSeries: String?,
    val firstName: String,
    val lastName: String,
    val middleName: String?
)

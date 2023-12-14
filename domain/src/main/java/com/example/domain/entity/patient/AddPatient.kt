package com.example.domain.entity.patient

data class AddPatient(
    val birthDateStr : String,
    val diagnosis: String?,
    val docNumber: Int?,
    val docSeries: Int?,
    val firstName: String,
    val lastName: String,
    val middleName: String?
)

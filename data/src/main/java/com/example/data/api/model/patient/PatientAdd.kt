package com.example.data.api.model.patient

data class PatientAdd(
    val birthDateStr : String,
    val diagnosis: String?,
    val docNumber: String?,
    val docSeries: String?,
    val firstName: String,
    val lastName: String,
    val middleName: String?
)

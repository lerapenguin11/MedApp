package com.example.data.api.model.patient

data class PatientAdd(
    val birthDateStr : String,
    val diagnosis: Any?,
    val docNumber: Any?,
    val docSeries: Any?,
    val firstName: String,
    val lastName: String,
    val middleName: Any?
)

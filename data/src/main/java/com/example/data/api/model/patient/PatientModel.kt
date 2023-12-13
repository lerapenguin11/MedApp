package com.example.data.api.model.patient

class PatientModel : ArrayList<PatientModelItem>()

data class PatientModelItem(
    val age: Any,
    val createdAt: String,
    val deletedAt: Any,
    val diagnosis: Any,
    val docNumber: Any,
    val docSeries: Any,
    val firstName: String,
    val id: Int,
    val lastName: String,
    val middleName: Any,
    val updatedAt: String
)
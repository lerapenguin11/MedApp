package com.example.home_data.mappers

import com.example.home_data.models.PatientModel
import com.example.home_domain.entity.Patients

class PatientApiResponseMapper
{
    fun toPatientList(response: PatientModel): List<Patients> {
        val list = arrayListOf<Patients>()
        for (i in response){
            val age = i.birthDateStr
            val createdAt = i.createdAt
            val deletedAt = i.deletedAt
            val diagnosis = i.diagnosis
            val docNumber = i.docNumber
            val docSeries = i.docSeries
            val firstName = i.firstName
            val id = i.id
            val lastName = i.lastName
            val middleName = i.middleName
            val updatedAt = i.updatedAt

            val patient = Patients(
                birthDateStr = age, createdAt = createdAt,
                deletedAt = deletedAt,
                diagnosis = diagnosis,
                docNumber = docNumber,
                docSeries = docSeries,
                firstName = firstName,
                id = id,
                lastName = lastName,
                middleName = middleName,
                updatedAt = updatedAt
            )

            list.add(patient)
        }
        return list
    }
}
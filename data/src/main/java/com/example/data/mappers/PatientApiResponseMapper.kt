package com.example.data.mappers

import com.example.data.api.model.patient.PatientModel
import com.example.domain.entity.Patient

class PatientApiResponseMapper {
    fun toPatientList(resp: PatientModel): List<Patient> {
        val patientList = arrayListOf<Patient>()
        for (i in resp) {
            val age = i.age
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

            val item = Patient(
                age = age, createdAt = createdAt,
                deletedAt = deletedAt,
                diagnosis = diagnosis,
                docNumber = docNumber,
                docSeries = docSeries,
            firstName = firstName,
            id = id,
            lastName = lastName,
            middleName = middleName,
            updatedAt = updatedAt)

            patientList.add(item)
        }
        return patientList
    }
}
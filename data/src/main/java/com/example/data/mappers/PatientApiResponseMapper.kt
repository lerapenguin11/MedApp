package com.example.data.mappers

import com.example.data.api.model.patient.NewPatientIdModel
import com.example.data.api.model.patient.PatientAdd
import com.example.data.api.model.patient.PatientModel
import com.example.domain.entity.patient.AddPatient
import com.example.domain.entity.patient.NewPatientId
import com.example.domain.entity.patient.Patients

class PatientApiResponseMapper {
    fun toPatientList(response: PatientModel): List<Patients> {
        var list = arrayListOf<Patients>()
        for (i in response){
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

            val patient = Patients(
                age = age, createdAt = createdAt,
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

    fun toPatientAdd(patient: AddPatient) : PatientAdd{
        return PatientAdd(
            age = patient.age,
            diagnosis = patient.diagnosis,
            docNumber = patient.docNumber,
            docSeries = patient.docSeries,
            firstName = patient.firstName,
            lastName = patient.lastName,
            middleName = patient.middleName,
        )
    }

    fun toNewPatientId(patientId: NewPatientIdModel) : NewPatientId {
        return NewPatientId(
            age = patientId.age,
            createdAt = patientId.createdAt,
            deletedAt = patientId.deletedAt,
            diagnosis = patientId?.diagnosis,
            docNumber = patientId.docNumber,
            docSeries = patientId.docSeries,
            firstName = patientId.firstName,
            id = patientId.id,
            lastName = patientId.lastName,
            middleName = patientId.middleName,
            updatedAt = patientId.updatedAt
        )
    }
}
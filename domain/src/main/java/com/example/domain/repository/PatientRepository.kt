package com.example.domain.repository

import com.example.domain.common.ResultMed
import com.example.domain.entity.patient.AddPatient
import com.example.domain.entity.patient.NewPatientId
import com.example.domain.entity.patient.Patients

interface PatientRepository {

    suspend fun getPatientList(): ResultMed<List<Patients>>

    suspend fun getAddPatient(patient : AddPatient) : ResultMed<NewPatientId?>

    suspend fun getNewPatientId(id : String) : ResultMed<NewPatientId>
}
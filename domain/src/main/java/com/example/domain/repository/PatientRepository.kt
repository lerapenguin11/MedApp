package com.example.domain.repository

import com.example.domain.common.ResultMed
import com.example.domain.entity.Patient

interface PatientRepository {

    suspend fun getPatientList(): ResultMed<List<Patient>>
}
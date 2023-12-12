package com.example.domain.usecase

import com.example.domain.entity.AddPatient
import com.example.domain.repository.PatientRepository

class GetAddPatientUseCase(private val repository: PatientRepository) {

    suspend operator fun invoke(patient : AddPatient) = repository.getAddPatient(patient = patient)
}
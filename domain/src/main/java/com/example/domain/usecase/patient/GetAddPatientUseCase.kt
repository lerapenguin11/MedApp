package com.example.domain.usecase.patient

import com.example.domain.entity.patient.AddPatient
import com.example.domain.repository.PatientRepository

class GetAddPatientUseCase(private val repository: PatientRepository) {

    suspend operator fun invoke(patient : AddPatient) = repository.getAddPatient(patient = patient)
}
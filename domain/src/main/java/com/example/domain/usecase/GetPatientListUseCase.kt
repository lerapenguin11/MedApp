package com.example.domain.usecase

import com.example.domain.repository.PatientRepository

class GetPatientListUseCase(private val repository: PatientRepository) {

    suspend operator fun invoke() = repository.getPatientList()
}
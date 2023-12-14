package com.example.domain.usecase.patient

import com.example.domain.entity.AddPatient
import com.example.domain.repository.PatientRepository

class GetPatientIdUseCase(private val repository: PatientRepository)
{
    suspend operator fun invoke(id : String) = repository.getNewPatientId(id = id)
}
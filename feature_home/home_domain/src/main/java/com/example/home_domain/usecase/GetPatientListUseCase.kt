package com.example.home_domain.usecase

import com.example.home_domain.repository.HomeRepository

class GetPatientListUseCase(
    private val repository: HomeRepository
)
{
    suspend operator fun invoke() = repository.getPatientList()
}
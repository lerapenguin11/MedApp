package com.example.home_domain.usecase

import com.example.home_domain.repository.HomeRepository
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class GetPatientListUseCase(
    private val repository: HomeRepository
)
{
    suspend operator fun invoke() = repository.getPatientList()
}
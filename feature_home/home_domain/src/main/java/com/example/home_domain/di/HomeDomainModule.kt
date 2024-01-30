package com.example.home_domain.di

import com.example.home_domain.repository.HomeRepository
import com.example.home_domain.usecase.GetPatientListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object HomeDomainModule {

    @Provides
    fun provideGetPatientListUseCase(repository: HomeRepository) : GetPatientListUseCase {
        return GetPatientListUseCase(repository)
    }
}



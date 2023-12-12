package com.example.medapp.di

import com.example.domain.usecase.GetAddPatientUseCase
import com.example.domain.usecase.GetPatientListUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<GetPatientListUseCase> { GetPatientListUseCase(
        repository = get()
    ) }

    factory<GetAddPatientUseCase> { GetAddPatientUseCase(
        repository = get()
    ) }
}
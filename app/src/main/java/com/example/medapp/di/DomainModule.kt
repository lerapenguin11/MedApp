package com.example.medapp.di

import com.example.domain.usecase.analysis.GetAddAnalysisUseCase
import com.example.domain.usecase.patient.GetAddPatientUseCase
import com.example.domain.usecase.patient.GetPatientIdUseCase
import com.example.domain.usecase.patient.GetPatientListUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<GetPatientListUseCase> { GetPatientListUseCase(
        repository = get()
    ) }

    factory<GetAddPatientUseCase> { GetAddPatientUseCase(
        repository = get()
    ) }

    factory<GetPatientIdUseCase> { GetPatientIdUseCase(
        repository = get()
    ) }

    factory<GetAddAnalysisUseCase> { GetAddAnalysisUseCase(
        repository = get()
    ) }
}
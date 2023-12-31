package com.example.medapp.di

import com.example.domain.usecase.analysis.GetAddAnalysisUseCase
import com.example.domain.usecase.analysis.GetAddCytokineStatusUseCase
import com.example.domain.usecase.analysis.GetAddHematologicalStatusUseCase
import com.example.domain.usecase.analysis.GetAddImmuneStatusUseCase
import com.example.domain.usecase.analysis.GetAnalysisListUseCase
import com.example.domain.usecase.analysis.GetPatientAnalysisListUseCase
import com.example.domain.usecase.analysis.GetUpdateAnalysisDateUseCase
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

    factory<GetAddHematologicalStatusUseCase> { GetAddHematologicalStatusUseCase(
        repository = get()
    ) }

    factory<GetUpdateAnalysisDateUseCase> { GetUpdateAnalysisDateUseCase(
        repository = get()
    ) }

    factory<GetAnalysisListUseCase> { GetAnalysisListUseCase(
        repository = get()
    ) }

    factory<GetAddImmuneStatusUseCase> { GetAddImmuneStatusUseCase(
        repository = get()
    ) }

    factory<GetAddCytokineStatusUseCase> { GetAddCytokineStatusUseCase(
        repository = get()
    ) }

    factory<GetPatientAnalysisListUseCase> { GetPatientAnalysisListUseCase(
        repository = get()
    ) }
}
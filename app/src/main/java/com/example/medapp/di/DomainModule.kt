package com.example.medapp.di

import com.example.domain.usecase.analysis.GetAddAnalysisUseCase
import com.example.domain.usecase.analysis.GetAddCytokineStatusUseCase
import com.example.domain.usecase.analysis.GetAddHematologicalStatusUseCase
import com.example.domain.usecase.analysis.GetAddImmuneStatusUseCase
import com.example.domain.usecase.analysis.GetAnalysisIdUseCase
import com.example.domain.usecase.analysis.GetAnalysisListUseCase
import com.example.domain.usecase.analysis.GetPatientAnalysisListUseCase
import com.example.domain.usecase.analysis.GetUpdateAnalysisDateUseCase
import com.example.domain.usecase.analysis.GetUpdateCytokineStatusUseCase
import com.example.domain.usecase.analysis.GetUpdateHematologicalStatusUseCase
import com.example.domain.usecase.analysis.GetUpdateImmuneStatusUseCase
import com.example.domain.usecase.analysis.GetValuesCytokineStatusUseCase
import com.example.domain.usecase.analysis.GetValuesHematologicalStatusUseCase
import com.example.domain.usecase.analysis.GetValuesImmuneStatusUseCase
import com.example.domain.usecase.graph.GetGraphUseCase
import com.example.domain.usecase.patient.GetAddPatientUseCase
import com.example.domain.usecase.patient.GetPatientIdUseCase
import com.example.domain.usecase.patient.GetPatientListUseCaseOld
import org.koin.dsl.module

val domainModule = module {

    factory<GetPatientListUseCaseOld> { GetPatientListUseCaseOld(
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

    factory<GetValuesHematologicalStatusUseCase> { GetValuesHematologicalStatusUseCase(
        repository = get()
    ) }

    factory<GetValuesCytokineStatusUseCase> { GetValuesCytokineStatusUseCase(
        repository = get()
    ) }

    factory<GetValuesImmuneStatusUseCase> { GetValuesImmuneStatusUseCase(
        repository = get()
    ) }

    factory<GetGraphUseCase> { GetGraphUseCase(
        repository = get()
    ) }

    factory<GetUpdateHematologicalStatusUseCase> { GetUpdateHematologicalStatusUseCase(
        repository = get()
    ) }

    factory<GetUpdateCytokineStatusUseCase> { GetUpdateCytokineStatusUseCase(
        repository = get()
    ) }

    factory<GetUpdateImmuneStatusUseCase> { GetUpdateImmuneStatusUseCase(
        repository = get()
    ) }

    factory<GetAnalysisIdUseCase> { GetAnalysisIdUseCase(
        repository = get()
    ) }
}
package com.example.medapp.di

import com.example.domain.usecase.analysis.GetAddAnalysisUseCase
import com.example.domain.usecase.analysis.GetAddHematologicalStatusUseCase
import com.example.domain.usecase.analysis.GetAddImmuneStatusUseCase
import com.example.domain.usecase.analysis.GetAnalysisListUseCase
import com.example.domain.usecase.analysis.GetUpdateAnalysisDateUseCase
import com.example.domain.usecase.patient.GetAddPatientUseCase
import com.example.domain.usecase.patient.GetPatientIdUseCase
import com.example.domain.usecase.patient.GetPatientListUseCase
import com.example.medapp.viewmodel.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel<HomeViewModel> {
        HomeViewModel(
            getPatientListUseCase = GetPatientListUseCase(get())
        )
    }

    viewModel<AddPatientViewModel> {
        AddPatientViewModel(
            getAddPatientUseCase = GetAddPatientUseCase(get())
        )
    }

    viewModel<ChangeInformationViewModel> {
        ChangeInformationViewModel(
            getPatientIdUseCase = GetPatientIdUseCase(get())
        )
    }

    viewModel<AddAnalysisViewModel> {
        AddAnalysisViewModel(
            getAddAnalysisUseCase = GetAddAnalysisUseCase(get()),
            getAddHematologicalStatusUseCase = GetAddHematologicalStatusUseCase(get()),
            getUpdateAnalysisDateUseCase = GetUpdateAnalysisDateUseCase(get()),
            getAddImmuneStatusUseCase = GetAddImmuneStatusUseCase(get())
        )
    }

    viewModel<DetailedInfoViewModel> {
        DetailedInfoViewModel(
            getPatientIdUseCase = GetPatientIdUseCase(get()),
            getAnalysisListUseCase = GetAnalysisListUseCase(get())
        )
    }
}
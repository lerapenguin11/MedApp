package com.example.medapp.di

import com.example.domain.usecase.analysis.GetAddAnalysisUseCase
import com.example.domain.usecase.patient.GetAddPatientUseCase
import com.example.domain.usecase.patient.GetPatientIdUseCase
import com.example.domain.usecase.patient.GetPatientListUseCase
import com.example.medapp.viewmodel.AddAnalysisViewModel
import com.example.medapp.viewmodel.AddPatientViewModel
import com.example.medapp.viewmodel.ChangeInformationViewModel
import com.example.medapp.viewmodel.HomeViewModel
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
            getAddAnalysisUseCase = GetAddAnalysisUseCase(get())
        )
    }
}
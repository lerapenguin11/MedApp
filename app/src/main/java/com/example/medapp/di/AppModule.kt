package com.example.medapp.di

import com.example.domain.usecase.GetPatientListUseCase
import com.example.medapp.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel<HomeViewModel> {
        HomeViewModel(
            getPatientListUseCase = GetPatientListUseCase(get())
        )
    }
}
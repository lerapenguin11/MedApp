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
import com.example.medapp.viewmodel.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel<HomeViewModelOld> {
        HomeViewModelOld(
            getPatientListUseCaseOld = GetPatientListUseCaseOld(get())
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
            getAddImmuneStatusUseCase = GetAddImmuneStatusUseCase(get()),
            getAddCytokineStatusUseCase = GetAddCytokineStatusUseCase(get()),
            getValuesHematologicalStatusUseCase = GetValuesHematologicalStatusUseCase(get()),
            getValuesCytokineStatusUseCase = GetValuesCytokineStatusUseCase(get()),
            getValuesImmuneStatusUseCase = GetValuesImmuneStatusUseCase(get())
        )
    }

    viewModel<DetailedInfoViewModel> {
        DetailedInfoViewModel(
            getPatientIdUseCase = GetPatientIdUseCase(get()),
            getAnalysisListUseCase = GetAnalysisListUseCase(get())
        )
    }

    viewModel<AnalysisViewModel> {
        AnalysisViewModel(
            getPatientAnalysisListUseCase = GetPatientAnalysisListUseCase(get())
        )
    }

    viewModel<GraphViewModel> {
        GraphViewModel(
            getGraphUseCase = GetGraphUseCase(get())
        )
    }

    viewModel<EditViewModel> {
        EditViewModel(
            getAnalysisIdUseCase = GetAnalysisIdUseCase(get()),
            getUpdateCytokineStatusUseCase = GetUpdateCytokineStatusUseCase(get()),
            getUpdateHematologicalStatusUseCase = GetUpdateHematologicalStatusUseCase(get()),
            getUpdateImmuneStatusUseCase = GetUpdateImmuneStatusUseCase(get())
        )
    }
}
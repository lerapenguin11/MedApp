package com.example.medapp.di

import com.example.data.api.NetworkModule
import com.example.data.mappers.AddAnalysisApiResponseMapper
import com.example.data.mappers.AnalysisApiResponseMapper
import com.example.data.mappers.PatientApiResponseMapper
import com.example.domain.repository.AddAnalysisRepository
import com.example.domain.repository.AnalysisRepository
import com.example.domain.repository.PatientRepository
import com.example.medapp.utilits.URL
import org.koin.dsl.module
import repository.addAnalysisRepository.AddAnalysisRemoteSource
import repository.addAnalysisRepository.AddAnalysisRemoteSourceImpl
import repository.addAnalysisRepository.AddAnalysisRepositoryImpl
import repository.analysisRepository.AnalysisRemoteSource
import repository.analysisRepository.AnalysisRemoteSourceImpl
import repository.analysisRepository.AnalysisRepositoryImpl
import repository.patientRepository.PatientRemoteSource
import repository.patientRepository.PatientRemoteSourceImpl
import repository.patientRepository.PatientRepositoryImpl

val dataModule = module {
    val networkModule by lazy {
        NetworkModule()
    }
    single<PatientRemoteSource> { PatientRemoteSourceImpl(get(), get()) }
    single<PatientRepository> { PatientRepositoryImpl(get()) }
    single { PatientApiResponseMapper() }

    single<AddAnalysisRemoteSource> { AddAnalysisRemoteSourceImpl(get(), get()) }
    single<AddAnalysisRepository> { AddAnalysisRepositoryImpl(get()) }
    single { AddAnalysisApiResponseMapper() }

    single<AnalysisRemoteSource> { AnalysisRemoteSourceImpl(get(), get()) }
    single<AnalysisRepository> { AnalysisRepositoryImpl(get()) }
    single { AnalysisApiResponseMapper() }

    single { networkModule.createApi(URL) }
}
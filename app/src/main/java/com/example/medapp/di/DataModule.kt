package com.example.medapp.di

import com.example.data.api.NetworkModule
import com.example.data.mappers.PatientApiResponseMapper
import com.example.domain.repository.PatientRepository
import com.example.medapp.utilits.URL
import org.koin.dsl.module
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
    single { networkModule.createApi(URL) }
}
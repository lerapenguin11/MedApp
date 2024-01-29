package com.example.home_data.di

import com.example.home_data.api.HomeApi
import com.example.home_data.mappers.PatientApiResponseMapper
import com.example.home_data.repository.PatientsRepositoryImpl
import com.example.home_domain.repository.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
object HomeDataModule
{
    @Provides
    fun providePatientApiService(retrofit : Retrofit) : HomeApi {
        return retrofit.create(HomeApi::class.java)
    }


    @Provides
    fun providePatientRepository(service : HomeApi,
                                 mapper : PatientApiResponseMapper) : HomeRepository {
        return PatientsRepositoryImpl(service = service, mapper = mapper)
    }
}
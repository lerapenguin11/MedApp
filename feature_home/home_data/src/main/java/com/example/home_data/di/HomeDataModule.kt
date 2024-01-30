package com.example.home_data.di

import com.example.home_data.api.HomeApi
import com.example.home_data.repository.PatientsRepositoryImpl
import com.example.home_domain.di.HomeDomainModule
import com.example.home_domain.repository.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object HomeDataModule {
    @Provides
    fun providePatientApiService(retrofit: Retrofit): HomeApi {
        return retrofit.create(HomeApi::class.java)
    }

    @Provides
    fun provideHomeRepositoryImpl(service: HomeApi) : HomeRepository {
        return PatientsRepositoryImpl(service)
    }
}

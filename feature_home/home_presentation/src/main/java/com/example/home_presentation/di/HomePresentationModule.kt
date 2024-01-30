package com.example.home_presentation.di

import android.app.Application
import android.content.Context
import android.content.res.Resources
import com.example.home_data.di.HomeDataModule
import com.example.home_domain.di.HomeDomainModule
import com.example.home_presentation.ui.HomeFragment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@InstallIn(FragmentComponent::class)
@Module(includes = [HomeDomainModule::class, HomeDataModule::class])
object HomePresentationModule
{
    @Provides
    fun provideFragment(): HomeFragment {
        return HomeFragment()
    }

    /*@Provides
    fun provideGetPatientListUseCase(viewModel : HomeViewModel) : GetPatientListUseCase {
        return viewModel.getPatientListUseCase
    }*/

    /*@Provides
    fun provideContext(app: Application) : Context = app.applicationContext

    @Provides
    fun provideResources(app: Application) : Resources = app.resources*/
}
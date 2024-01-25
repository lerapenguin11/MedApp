package com.example.home_presentation.di

import com.example.home_presentation.ui.HomeFragment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object HomeModule
{
    @Provides
    fun provideFragment(): HomeFragment {
        return HomeFragment()
    }
}
package com.example.medapp

import com.example.medapp.presentation.SplashScreenFragment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.FragmentScoped
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ActivityComponent::class)
object MainModule {
    @ActivityScoped
    @Provides
    fun provideFragment(): SplashScreenFragment {
        return SplashScreenFragment()
    }
}

package com.example.navigation

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NavigationModule {

    @Provides
    fun provideFragmentManager(activity: AppCompatActivity): FragmentManager {
        return activity.supportFragmentManager
    }

    @Provides
    fun provideNavigator(fragmentManager: FragmentManager): Navigator {
        return Navigator(fragmentManager)
    }

    @Provides
    fun provideAppCompatActivity(): AppCompatActivity {
        return AppCompatActivity()
    }
}
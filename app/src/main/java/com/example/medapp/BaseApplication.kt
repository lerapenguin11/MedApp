package com.example.medapp

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import com.example.navigation.NavigationModule
import dagger.Component
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

@HiltAndroidApp
class BaseApplication : Application(){
}


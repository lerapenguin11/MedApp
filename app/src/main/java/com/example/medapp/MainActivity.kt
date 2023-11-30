package com.example.medapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.medapp.databinding.ActivityMainBinding
import com.example.medapp.presentation.SplashScreenFragment
import com.example.medapp.utilits.APP_ACTIVITY
import com.example.medapp.utilits.replaceFragmentMain

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        APP_ACTIVITY = this
        replaceFragmentMain(SplashScreenFragment())
    }
}
package com.example.medapp.presentation

import android.animation.Animator
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.medapp.R
import com.example.medapp.databinding.FragmentSplashScreenBinding
import com.example.medapp.utilits.replaceFragmentMain

class SplashScreenFragment : Fragment() {
    private var _binding : FragmentSplashScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashScreenBinding.inflate(inflater, container, false)

        //TODO дописать
        Handler(Looper.getMainLooper()).postDelayed({
            replaceFragmentMain(AuthorizationFragment())
        }, 3000)

        return binding.root
    }
}
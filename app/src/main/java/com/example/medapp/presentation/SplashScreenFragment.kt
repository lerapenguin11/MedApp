package com.example.medapp.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.home_presentation.ui.HomeFragment
import com.example.medapp.databinding.FragmentSplashScreenBinding
import com.example.navigation.Navigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashScreenFragment : Fragment() {
    @Inject
    lateinit var navigator : Navigator
    private var _binding : FragmentSplashScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashScreenBinding.inflate(inflater, container, false)

        //TODO дописать
        Handler(Looper.getMainLooper()).postDelayed({
            navigateToNextFragment()
        }, 3000)

        return binding.root
    }

    private fun navigateToNextFragment() {
        navigator = Navigator(fragmentManager = fragmentManager)
        navigator.navigateToFragmentAddBackStack(HomeFragment())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
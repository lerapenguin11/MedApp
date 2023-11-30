package com.example.medapp.presentation

import android.animation.Animator
import android.os.Bundle
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

        binding.titleSplash.animate().translationX(1400f).setDuration(1000).setStartDelay(4000)

        binding.anim.animate().translationX(1400f).setDuration(1000).setStartDelay(4000)
            .setListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {}
                override fun onAnimationEnd(animation: Animator) {
                    //TODO дописать
                    replaceFragmentMain(AuthorizationFragment())
                }

                override fun onAnimationCancel(animation: Animator) {}
                override fun onAnimationRepeat(animation: Animator) {}
            })

        return binding.root
    }
}
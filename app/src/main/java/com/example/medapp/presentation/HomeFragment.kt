package com.example.medapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.medapp.databinding.FragmentHomeBinding
import com.example.medapp.presentation.adapter.PatientAdapter
import com.example.medapp.utilits.replaceFragmentMain
import com.example.medapp.viewmodel.HomeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel by viewModel<HomeViewModel>()
    private lateinit var adapter :  PatientAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel.getPatient()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        setOnClickListenerAddPatient()
        setPatientRecyclerView()

        return binding.root
    }

    private fun setPatientRecyclerView() {
        adapter =  PatientAdapter()
        homeViewModel.patients.observe(viewLifecycleOwner, Observer { patient ->
            adapter.submitList(patient)
        })
        binding.rvPatients.adapter = adapter
    }

    private fun setOnClickListenerAddPatient() {
        binding.btAddPatient.setOnClickListener {
            replaceFragmentMain(AddPatientFragment())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.rvPatients.adapter = null
        _binding = null
    }
}
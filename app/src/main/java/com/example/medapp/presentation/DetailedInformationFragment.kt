package com.example.medapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.medapp.R
import com.example.medapp.databinding.FragmentDetailedInformationBinding
import com.example.medapp.databinding.FragmentHomeBinding
import com.example.medapp.viewmodel.ChangeInformationViewModel
import com.example.medapp.viewmodel.DetailedInfoViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailedInformationFragment : Fragment() {
    private var _binding : FragmentDetailedInformationBinding? = null
    private val binding get() = _binding!!
    private var idPatient: String? = null
    private val detailedInfoViewModel by viewModel<DetailedInfoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            idPatient = it.getString(BUNDLE_PATIENT_ID)
        }
        idPatient?.let { detailedInfoViewModel.getPatientId(id = it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailedInformationBinding.inflate(inflater, container, false)
        setDataPatient()
        setOnClickListenerButton()
        return binding.root
    }

    private fun setOnClickListenerButton() {
        binding.icExit.setOnClickListener { launchFragment(HomeFragment()) }
    }

    private fun launchFragment(fragment: Fragment){
        fragmentManager?.popBackStack()
        fragmentManager?.beginTransaction()
            ?.replace(R.id.main_layout, fragment)
            ?.addToBackStack(null)
            ?.commit()
    }

    private fun setDataPatient() {
        detailedInfoViewModel.patientId.observe(viewLifecycleOwner, Observer {patient->
            binding.tvSurnamePatient.text = patient?.lastName
            binding.tvNamePatient.text = patient?.firstName
            binding.tvPatronymicPatient.text = patient?.middleName
            binding.tvDatePatient.text = patient?.birthDateStr.toString()
            binding.tvDiagnosis.text = patient?.diagnosis
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val BUNDLE_PATIENT_ID = "patient_id"
    }
}
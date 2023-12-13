package com.example.medapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.domain.entity.AddPatient
import com.example.medapp.R
import com.example.medapp.databinding.FragmentAddPatientBinding
import com.example.medapp.viewmodel.AddPatientViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddPatientFragment : Fragment() {
    private var _binding : FragmentAddPatientBinding? = null
    private val binding get() = _binding!!
    private val addPatientViewModel by viewModel<AddPatientViewModel>()
    private var idPatient = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddPatientBinding.inflate(inflater, container, false)
        setOnClickButton()
        return binding.root
    }

    private fun setOnClickButton() {
        binding.btContinue.setOnClickListener {
            readingDataEditText()
            receivingPatientId()
        }
        binding.icExit.setOnClickListener {launchFragment(HomeFragment())}
    }

    private fun receivingPatientId() {
        addPatientViewModel.patientId.observe(viewLifecycleOwner, Observer {
            if (it?.id != -1){
                idPatient = it?.id.toString()
                val transaction = activity?.supportFragmentManager?.beginTransaction()
                transaction?.replace(R.id.main_layout, newInstancePatientId(id = idPatient))
                transaction?.commit()
            }
        })
    }

    private fun launchFragment(fragment: Fragment){
        fragmentManager?.popBackStack()
        fragmentManager?.beginTransaction()
            ?.replace(R.id.main_layout, fragment)
            ?.addToBackStack(null)
            ?.commit()
    }

    private fun readingDataEditText() {
        addPatientViewModel.addPatient(AddPatient(age = null,
            diagnosis = null, docNumber = null, docSeries = null,
            firstName = binding.etNamePatient.text.toString(),
            lastName = binding.etSurnamePatient.text.toString(),
            middleName = null))
    }

    companion object {
        private const val BUNDLE_PATIENT_ID = "patient_id"

        fun newInstancePatientId(id : String) =
            ChangeInformationFragment().apply {
                arguments = Bundle().apply {
                    putString(BUNDLE_PATIENT_ID, id)
                }
            }
    }
}
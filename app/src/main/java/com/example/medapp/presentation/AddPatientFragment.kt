package com.example.medapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.domain.entity.AddPatient
import com.example.medapp.R
import com.example.medapp.databinding.FragmentAddPatientBinding
import com.example.medapp.databinding.FragmentAuthorizationBinding
import com.example.medapp.utilits.replaceFragmentMain
import com.example.medapp.viewmodel.AddPatientViewModel
import com.example.medapp.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddPatientFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddPatientFragment : Fragment() {
    private var _binding : FragmentAddPatientBinding? = null
    private val binding get() = _binding!!
    private val addPatientViewModel by viewModel<AddPatientViewModel>()

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddPatientBinding.inflate(inflater, container, false)

        binding.btContinue.setOnClickListener {
            readingDataEditText()
            replaceFragmentMain(AddAnalysisFragment())
        }


        return binding.root
    }

    private fun readingDataEditText() {
        addPatientViewModel.addPatient(AddPatient(age = null,
            diagnosis = null, docNumber = null, docSeries = null,
            firstName = binding.etNamePatient.text.toString(),
            lastName = binding.etSurnamePatient.text.toString(),
            middleName = null))
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddPatientFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddPatientFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
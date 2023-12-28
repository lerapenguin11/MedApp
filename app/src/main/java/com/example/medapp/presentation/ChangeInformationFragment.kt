package com.example.medapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.medapp.R
import com.example.medapp.databinding.FragmentChangeInformationBinding
import com.example.medapp.utilits.replaceFragmentMain
import com.example.medapp.viewmodel.ChangeInformationViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChangeInformationFragment : Fragment() {
    private var _binding : FragmentChangeInformationBinding? = null
    private val binding get() = _binding!!
    private val changeInfoViewModel by viewModel<ChangeInformationViewModel>()
    private var idPatient: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            idPatient = it.getString(BUNDLE_PATIENT_ID)
        }
        idPatient?.let { changeInfoViewModel.getPatientId(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChangeInformationBinding.inflate(inflater, container, false)
        shimmerEffectPlaceholder()
        initCard()
        setOnClickListener()

        return binding.root
    }

    private fun shimmerEffectPlaceholder() {
        binding.shimmerLayout.startShimmer()
    }

    private fun setOnClickListener() {
        binding.btSave.setOnClickListener {
            launchFragment(HomeFragment())
        }
        //TODO проверить
        binding.btAddAnalyxes.setOnClickListener {
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            idPatient?.let { it1 -> ChangeInformationFragment.newInstancePatientId(id = it1) }
                ?.let { it2 ->
                    transaction?.replace(R.id.main_layout,
                        it2
                    )
                }
            transaction?.commit()
        }
    }

    override fun onPause() {
        super.onPause()
        binding.shimmerLayout.stopShimmer()
    }

    override fun onResume() {
        super.onResume()
        binding.shimmerLayout.startShimmer()
    }

    private fun launchFragment(fragment: Fragment){
        fragmentManager?.popBackStack()
        fragmentManager?.beginTransaction()
            ?.replace(R.id.main_layout, fragment)
            ?.addToBackStack(null)
            ?.commit()
    }

    private fun initCard() {
        changeInfoViewModel.patientId.observe(viewLifecycleOwner, Observer {patient ->
            binding.tvNamePatient.text = patient?.firstName
            binding.tvPatronymicPatient.text = patient?.middleName.toString()
            binding.tvSurnamePatient.text = patient?.lastName
            binding.tvDiagnosis.text = patient?.diagnosis.toString()
            binding.tvDatePatient.text = patient?.birthDateStr.toString()
            binding.tvDateLastAnalysis.text = DATE_LAST_ANALYSIS
            binding.shimmerLayout.stopShimmer()
            binding.shimmerLayout.visibility = View.GONE
            binding.blockChangeInfo.visibility = View.VISIBLE
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val BUNDLE_PATIENT_ID = "patient_id"
        private const val DATE_LAST_ANALYSIS = "Нет"

        fun newInstancePatientId(id : String) =
            AddAnalysisFragment().apply {
                arguments = Bundle().apply {
                    putString(BUNDLE_PATIENT_ID, id)
                }
            }
    }
}
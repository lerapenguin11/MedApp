package com.example.medapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.medapp.R
import com.example.medapp.databinding.FragmentDetailedInformationBinding
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
        idPatient?.let { detailedInfoViewModel.getAnalysisList(idPatient = it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailedInformationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shimmerEffectPlaceholder()
        setData()
        setOnClickListenerButton()
    }

    private fun setOnClickListenerButton() {
        binding.icExit.setOnClickListener { launchFragment(HomeFragment()) }
        binding.btLearnAboutAnalyses.setOnClickListener {
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            idPatient?.let { it1 -> newInstancePatientId(id = it1) }?.let { it2 ->
                transaction?.replace(
                    R.id.main_layout,
                    it2
                )?.addToBackStack(null)
            }
            transaction?.commit()
        }
    }

    private fun launchFragment(fragment: Fragment){
        fragmentManager?.popBackStack()
        fragmentManager?.beginTransaction()
            ?.replace(R.id.main_layout, fragment)
            ?.addToBackStack(null)
            ?.commit()
    }

    private fun setData() {
        detailedInfoViewModel.patientId.observe(viewLifecycleOwner, Observer {patient->
            binding.tvSurnamePatient.text = patient?.lastName
            binding.tvNamePatient.text = patient?.firstName
            binding.tvPatronymicPatient.text = patient?.middleName
            binding.tvDatePatient.text = patient?.birthDateStr.toString()
            binding.tvDiagnosis.text = patient?.diagnosis
        })
        detailedInfoViewModel.analysisList.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                if (it?.last()?.executionDateStr == null || it.isEmpty()){
                    binding.tvDateLastAnalysis.text = CONST_DATE_LAST_ANALYSIS
                } else{
                    binding.tvDateLastAnalysis.text = it.last().executionDateStr
                }
            }
        })
        binding.shimmerLayout.stopShimmer()
        binding.shimmerLayout.visibility = View.GONE
        binding.blockDetailInfo.visibility = View.VISIBLE
    }

    private fun shimmerEffectPlaceholder() {
        binding.shimmerLayout.startShimmer()
    }

    override fun onPause() {
        super.onPause()
        binding.shimmerLayout.stopShimmer()
    }

    override fun onResume() {
        super.onResume()
        binding.shimmerLayout.startShimmer()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val BUNDLE_PATIENT_ID = "patient_id"
        private const val CONST_DATE_LAST_ANALYSIS = "Нет"

        fun newInstancePatientId(id : String) =
            AnalyzesFragment().apply {
                arguments = Bundle().apply {
                    putString(BUNDLE_PATIENT_ID, id)
                }
            }
    }
}
package com.example.medapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.medapp.R
import com.example.medapp.databinding.FragmentAnalyzesBinding
import com.example.medapp.presentation.adapter.AnalysisAdapter
import com.example.medapp.presentation.adapter.BottomSpaceItemDecoration
import com.example.medapp.viewmodel.AnalysisViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AnalyzesFragment : Fragment() {
    private var _binding : FragmentAnalyzesBinding? = null
    private val binding get() = _binding!!
    private var idPatient: String? = null
    private val analysisViewModel by viewModel<AnalysisViewModel>()
    private val adapter = AnalysisAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            idPatient = it.getString(BUNDLE_PATIENT_ID)
        }
        idPatient?.let { analysisViewModel.getAnalysisList(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAnalyzesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerViewAnalysis()
        setOnClickListenerBtAddAnalysis()
        setOnClickListenerBackArrow()
        setOnClickListenerItemAnalysis()
    }

    private fun setOnClickListenerItemAnalysis() {
        adapter.onAnalysisClickListener = {
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(
                R.id.main_layout,
                newInstanceAnalysisId(idAnalysis = it.id, it.patientId.toString())
            )?.addToBackStack(null)
            transaction?.commit()
        }
    }

    private fun setOnClickListenerBackArrow() {
        binding.icExit.setOnClickListener {
            if (idPatient != null){
                val transaction = activity?.supportFragmentManager?.beginTransaction()
                transaction?.replace(
                    R.id.main_layout,
                    newInstancePatientId(id = idPatient!!)
                )?.addToBackStack(null)
                transaction?.commit()
            }
        }
    }

    private fun setOnClickListenerBtAddAnalysis() {
        binding.btAddAnalysis.setOnClickListener {
            if (idPatient != null){
                val transaction = activity?.supportFragmentManager?.beginTransaction()
                transaction?.replace(
                    R.id.main_layout,
                    newInstancePatientIdAddAnalysis(id = idPatient!!)
                )?.addToBackStack(null)
                transaction?.commit()
            }
        }
    }

    private fun setRecyclerViewAnalysis() {
        val bottomSpaceItemDecoration =
            BottomSpaceItemDecoration(resources.getDimensionPixelSize(R.dimen.bottom_space))
        analysisViewModel.analysisList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
        binding.rvAnalyzes.adapter = adapter
        binding.rvAnalyzes.addItemDecoration(bottomSpaceItemDecoration)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.rvAnalyzes.adapter = null
        _binding = null
    }

    companion object {
        private const val BUNDLE_PATIENT_ID = "patient_id"
        private const val BUNDLE_ANALYSIS_ID = "analysis_id"

        fun newInstancePatientIdAddAnalysis(id : String) =
            AddAnalysisFragment().apply {
                arguments = Bundle().apply {
                    putString(BUNDLE_PATIENT_ID, id)
                }
            }

        fun newInstancePatientId(id : String) =
            DetailedInformationFragment().apply {
                arguments = Bundle().apply {
                    putString(BUNDLE_PATIENT_ID, id)
                }
            }

        fun newInstanceAnalysisId(idAnalysis : Int, idPatient : String) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putInt(BUNDLE_ANALYSIS_ID, idAnalysis)
                    putString(BUNDLE_PATIENT_ID, idPatient)
                }
            }
    }
}
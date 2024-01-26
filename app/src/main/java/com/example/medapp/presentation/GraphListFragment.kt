package com.example.medapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.medapp.R
import com.example.medapp.databinding.FragmentGraphListBinding

class GraphListFragment : Fragment() {
    private var _binding : FragmentGraphListBinding? = null
    private val binding get() = _binding!!
    private var idAnalysis : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            idAnalysis = it.getString(BUNDLE_ANALYSIS_ID).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGraphListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListenerGraph()
    }

    private fun setOnClickListenerGraph() {
        binding.blockTCell.setOnClickListener{
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(
                R.id.main_layout,
                newInstanceAnalysisId(id = idAnalysis, CODE_T_CELL)
            )?.addToBackStack(null)
            transaction?.commit()
        }

        binding.blockBCell.setOnClickListener {
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(
                R.id.main_layout,
                newInstanceAnalysisId(id = idAnalysis, CODE_B_CELL)
            )?.addToBackStack(null)
            transaction?.commit()
        }
    }

    companion object {
        private const val BUNDLE_ANALYSIS_ID = "analysis_id"
        private const val CODE_T_CELL = 100
        private const val CODE_B_CELL = 200
        private const val BUNDLE_CODE = "code_cell"
        fun newInstanceAnalysisId(id : String, code : Int) =
            ChartFragment().apply {
                arguments = Bundle().apply {
                    putString(BUNDLE_ANALYSIS_ID, id)
                    putInt(BUNDLE_CODE, code)
                }
            }
    }
}
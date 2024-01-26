package com.example.medapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.medapp.R
import com.example.medapp.databinding.FragmentChartBinding
import com.example.medapp.viewmodel.GraphViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class ChartFragment : Fragment() {
    private var idAnalysis : String? = null
    private var _binding : FragmentChartBinding? = null
    private var codeCell = -1
    private val binding get() = _binding!!
    private val graphViewModel by viewModel<GraphViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            idAnalysis = it.getString(BUNDLE_ANALYSIS_ID)
            codeCell = it.getInt(BUNDLE_CODE)
        }
        graphViewModel.getGraphTCell(idAnalysis.toString())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setImageGraph()
        setOnClickListener()
    }

    private fun setOnClickListener() {
        binding.icExit.setOnClickListener {
            if (idAnalysis != null){
                val transaction = activity?.supportFragmentManager?.beginTransaction()
                fragmentManager?.popBackStack()
                transaction?.replace(
                    R.id.main_layout,
                    newInstanceAnalysisId(
                        id = idAnalysis.toString(),
                    )
                )?.addToBackStack(null)
                transaction?.commit()
            }
        }
    }

    private fun setImageGraph() {
        when(codeCell){
            100 ->{
                graphViewModel.getGraphTCell(idAnalysis.toString())
                setDataChartTCell()
            }
            200 ->{
                graphViewModel.getGraphBCell(idAnalysis.toString())
                setDataChartBCell()
            }
        }
    }

    private fun setDataChartBCell() {
        binding.titleChart.setText(R.string.text_b_cell)
        binding.titleChart.isSelected = true
        graphViewModel.graphB.observe(viewLifecycleOwner, Observer {
            binding.pvGraph.setImageBitmap(it)
        })
    }

    private fun setDataChartTCell() {
        binding.titleChart.setText(R.string.text_t_cell)
        binding.titleChart.isSelected = true
        graphViewModel.graphT.observe(viewLifecycleOwner, Observer {
            binding.pvGraph.setImageBitmap(it)
        })
    }

    /*setAnimationText()*/

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val BUNDLE_ANALYSIS_ID = "analysis_id"
        private const val BUNDLE_CODE = "code_cell"
        fun newInstanceAnalysisId(id : String) =
            GraphListFragment().apply {
                arguments = Bundle().apply {
                    putString(BUNDLE_ANALYSIS_ID, id)
                }
            }
    }
}
package com.example.medapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.medapp.R
import com.example.medapp.databinding.FragmentChartBinding
import com.example.medapp.databinding.FragmentDetailsBinding
import com.example.medapp.viewmodel.GraphViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class ChartFragment : Fragment() {
    private var idAnalysis : String? = null
    private var _binding : FragmentChartBinding? = null
    private val binding get() = _binding!!
    private val graphViewModel by viewModel<GraphViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            idAnalysis = it.getString(BUNDLE_ANALYSIS_ID)
        }
        graphViewModel.getGraph(idAnalysis.toString())
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
        binding.icExit.setOnClickListener {  }
    }

    private fun setImageGraph() {
        graphViewModel.graph.observe(viewLifecycleOwner, Observer {
            binding.pvGraph.setImageBitmap(it)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val BUNDLE_ANALYSIS_ID = "analysis_id"
    }
}
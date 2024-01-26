package com.example.medapp.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import com.example.medapp.R
import com.example.medapp.databinding.FragmentAnalyzesBinding
import com.example.medapp.databinding.FragmentDetailsBinding
import com.example.medapp.viewmodel.AnalysisViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : Fragment() {
    private var _binding : FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private var idPatient : String? = null
    private var idAnalysis : Int? = null
    private val analysisViewModel by viewModel<AnalysisViewModel>()
    private var checkDateAnalysis = true
    private var checkHematologicalStatus = true
    private var checkImmuneStatus = true
    private var checkCytokineStatus = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            idPatient = it.getString(BUNDLE_PATIENT_ID)
            idAnalysis = it.getInt(BUNDLE_ANALYSIS_ID)
        }
        idPatient?.let { analysisViewModel.getAnalysisList(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        analysisViewModel.analysisList.observe(viewLifecycleOwner, Observer {list ->
            idAnalysis?.let { list?.let { it1 -> analysisViewModel.filterAnalysisList(idAnalysis = it, analysisList = it1) } }
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collapseExpandBlockInput()
        setTextHematologicalStatus()
        setTextImmuneStatus()
        setTextCytokineStatus()
        setTextDate()
        setOnClickListenerBackArrow()
        setOnClickChartsBt()
        setUpdateOnClickListener()
    }

    private fun setUpdateOnClickListener() {
        binding.icEdit.setOnClickListener {
            if (idAnalysis != null){
                fragmentManager?.popBackStack()
                val transaction = activity?.supportFragmentManager?.beginTransaction()
                transaction?.replace(
                    R.id.main_layout,
                    newInstanceAnalysisIdUpdate(id = idAnalysis.toString())
                )
                transaction?.commit()
            }
        }
    }

    private fun setOnClickChartsBt() {
        binding.btOpenCharts.setOnClickListener {
            if (idAnalysis != null){
                val transaction = activity?.supportFragmentManager?.beginTransaction()
                transaction?.replace(
                    R.id.main_layout,
                    newInstanceAnalysisId(id = idAnalysis.toString())
                )?.addToBackStack(null)
                transaction?.commit()
            }
        }
    }

    private fun setOnClickListenerBackArrow() {
        binding.icExit.setOnClickListener{
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

    private fun setTextCytokineStatus() {
        analysisViewModel.analysis.observe(viewLifecycleOwner, Observer { analysis ->
            println(analysis)
            binding.tvIFNySpontaneous.text = analysis?.cytokineStatus?.cd3_m_ifny_spontaneous.toString()
            binding.tvIFNyStimulated.text = analysis?.cytokineStatus?.cd3_m_ifny_stimulated.toString()
            binding.tvIfnySpontaneous.text = analysis?.cytokineStatus?.cd3_p_ifny_spontaneous.toString()
            binding.tvCd3IfnyStimulated.text = analysis?.cytokineStatus?.cd3_p_ifny_stimulated.toString()
            binding.tvIL2TnnySpontaneous.text = analysis?.cytokineStatus?.cd3_p_il2_spontaneous.toString()
            binding.tvIL2TnnyStimulated.text = analysis?.cytokineStatus?.cd3_p_il2_stimulated.toString()
            binding.tvIL4Spontaneous.text = analysis?.cytokineStatus?.cd3_p_il4_spontaneous.toString()
            binding.tvIL4Stimulated.text = analysis?.cytokineStatus?.cd3_p_il4_stimulated.toString()
            binding.tvCd3TnnySpontaneous.text = analysis?.cytokineStatus?.cd3_p_tnfa_spontaneous.toString()
            binding.tvCd3TnnyStimulated.text = analysis?.cytokineStatus?.cd3_p_tnfa_stimulated.toString()
        })
    }

    private fun setTextImmuneStatus() {
        analysisViewModel.analysis.observe(viewLifecycleOwner, Observer {analysis ->
            binding.tvActivatedTCells.text = analysis?.immuneStatus?.activated_t_cells.toString()
            binding.tvActivatedTCellsExpressingLitr.text =
                analysis?.immuneStatus?.activated_t_cells_expressing_il2.toString()
            binding.tvRatio.text = analysis?.immuneStatus?.cd3_p_cd4_p_cd3_p_cd8_p_ratio.toString()
            binding.tvCirculatingImmuneComplexes.text =
                analysis?.immuneStatus?.circulating_immune_complexes.toString()
            binding.tvCommonBLymphocytes.text =
                analysis?.immuneStatus?.common_b_lymphocytes.toString()
            binding.tvCommonNkCellsLitr.text = analysis?.immuneStatus?.common_nk_cells.toString()
            binding.tvCommonLymphocytes.text =
                analysis?.immuneStatus?.common_t_lymphocytes.toString()
            binding.tvNkCytokineProducingCells.text =
                analysis?.immuneStatus?.cytokine_producing_nk_cells.toString()
            binding.tvNkCellsCytolytic.text = analysis?.immuneStatus?.cytolytic_nk_cells.toString()
            binding.tvHCTTestSpontaneous.text = analysis?.immuneStatus?.hct_test_spontaneous.toString()
            binding.tvHCTTestStimulated.text = analysis?.immuneStatus?.hct_test_stimulated.toString()
            binding.tvBactericidalActivityLeukocytes.text =
                analysis?.immuneStatus?.leukocytes_bactericidal_activity.toString()
            binding.tvIga.text = analysis?.immuneStatus?.lga.toString()
            binding.tvIgg.text = analysis?.immuneStatus?.lgg.toString()
            binding.tvIgm.text = analysis?.immuneStatus?.lgm.toString()
            binding.tvAbsorptionActivityMonocytesLitr.text =
                analysis?.immuneStatus?.monocytes_absorption_activity.toString()
            binding.tvAbsorptionActivityNeutrophilsLitr.text =
                analysis?.immuneStatus?.neutrophil_absorption_activity.toString()
            binding.tvCytotoxicLymphocytesLitr.text = analysis?.immuneStatus?.t_cytotoxic_lymphocytes.toString()
            binding.tvHelpersLitr.text = analysis?.immuneStatus?.t_helpers.toString()
            binding.tvTnkCellsLitr.text = analysis?.immuneStatus?.tnk_cells.toString()
        })
    }
    private fun setTextDate() {
        analysisViewModel.analysis.observe(viewLifecycleOwner, Observer {analysis->
            binding.tvDateAnalysis.text = analysis?.executionDateStr
        })
    }
    private fun setTextHematologicalStatus() {
        analysisViewModel.analysis.observe(viewLifecycleOwner, Observer {analysis->
            binding.tvLeukocyte.text = analysis?.hematologicalStatus?.wbc.toString()
            binding.tvLymphocytesPercentage.text = analysis?.hematologicalStatus?.lymf.toString()
            binding.tvMonocytesPercentage.text = analysis?.hematologicalStatus?.mon.toString()
            binding.tvNeutrophilsPercentage.text = analysis?.hematologicalStatus?.neu.toString()
            binding.tvEosinophilsPercentage.text = analysis?.hematologicalStatus?.eos.toString()
            binding.tvBasophilsPercentage.text = analysis?.hematologicalStatus?.bas.toString()
            binding.tvHemoglobin.text = analysis?.hematologicalStatus?.hgb.toString()
            binding.tvHematocrit.text = analysis?.hematologicalStatus?.hct.toString()
            binding.tvPlatelets.text = analysis?.hematologicalStatus?.plt.toString()
            binding.tvErythrocyte.text = analysis?.hematologicalStatus?.rbc.toString()
            binding.tvMcv.text = analysis?.hematologicalStatus?.mcv.toString()
            binding.tvMch.text = analysis?.hematologicalStatus?.mch.toString()
            binding.tvMchc.text = analysis?.hematologicalStatus?.mchc.toString()
            binding.tvRdwCv.text = analysis?.hematologicalStatus?.rdwcv.toString()
            binding.tvMpv.text = analysis?.hematologicalStatus?.mpv.toString()
            binding.tvPct.text = analysis?.hematologicalStatus?.pct.toString()
            binding.tvPdv.text = analysis?.hematologicalStatus?.pdv.toString()
        })
    }
    private fun collapseExpandBlockInput() {
        binding.btArrowDateAnalize.setOnClickListener {
            checkDateAnalysis = replacementArrow(binding.icArrowDateAnalysis, checkDateAnalysis,
                binding.blockDateAnalysis)
        }
        binding.btArrowHematologicalStatus.setOnClickListener {
            checkHematologicalStatus = replacementArrow(binding.icArrowHematologicalStatus,
                checkHematologicalStatus, binding.linearLayoutHematologicalStatus)
        }
        binding.btArrowImmuneStatus.setOnClickListener {
            checkImmuneStatus = replacementArrow(binding.icArrowImmuneStatus, checkImmuneStatus,
                binding.blockImmuneStatus)
        }
        binding.btArrowCytokineStatus.setOnClickListener {
            checkCytokineStatus = replacementArrow(binding.icArrowCytokineStatus,
                checkCytokineStatus, binding.blockCytokineStatus)
        }
    }

    private fun replacementArrow(imageView: ImageView,
                                 check : Boolean, block : LinearLayout
    ) : Boolean{
        return if (check){
            imageView.setImageResource(R.drawable.ic_arrow_top)
            block.visibility = View.VISIBLE
            false
        } else{
            imageView.setImageResource(R.drawable.ic_arrow_bottom)
            block.visibility = View.GONE
            true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val BUNDLE_PATIENT_ID = "patient_id"
        private const val BUNDLE_ANALYSIS_ID = "analysis_id"

        fun newInstancePatientId(id : String) =
            AnalyzesFragment().apply {
                arguments = Bundle().apply {
                    putString(BUNDLE_PATIENT_ID, id)
                }
            }

        fun newInstanceAnalysisId(id : String) =
            GraphListFragment().apply {
                arguments = Bundle().apply {
                    putString(BUNDLE_ANALYSIS_ID, id)
                }
            }

        fun newInstanceAnalysisIdUpdate(id : String) =
            EditFragment().apply {
                arguments = Bundle().apply {
                    putString(BUNDLE_ANALYSIS_ID, id)
                }
            }
    }
}
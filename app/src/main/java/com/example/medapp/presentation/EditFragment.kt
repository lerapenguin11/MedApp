package com.example.medapp.presentation

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.analysis.CytokineStatus
import com.example.domain.entity.analysis.HematologicalStatus
import com.example.domain.entity.analysis.ImmuneStatus
import com.example.medapp.R
import com.example.medapp.databinding.FragmentEditBinding
import com.example.medapp.presentation.adapter.UpdateStatusCytokineAdapter
import com.example.medapp.presentation.adapter.UpdateStatusHematologicalAdapter
import com.example.medapp.presentation.adapter.UpdateStatusImmuneAdapter
import com.example.medapp.utilits.replaceFragmentMain
import com.example.medapp.viewmodel.AddAnalysisViewModel
import com.example.medapp.viewmodel.EditViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditFragment : Fragment() {
    private var _binding : FragmentEditBinding? = null
    private val binding get() = _binding!!
    private var checkDateAnalysis = true
    private var checkHematologicalStatus = true
    private var checkImmuneStatus = true
    private var checkCytokineStatus = true
    private var idPatient : String = ""
    private val addAnalysisViewModel by viewModel<AddAnalysisViewModel>()
    private val analysisViewModel by viewModel<EditViewModel>()
    private var idAnalysis : String = ""
    private lateinit var adapterHematological : UpdateStatusHematologicalAdapter
    private lateinit var adapterImmune : UpdateStatusImmuneAdapter
    private lateinit var adapterCytokine : UpdateStatusCytokineAdapter

    private var hematologicalList = arrayListOf<Double>()
    private var immuneList = arrayListOf<Double>()
    private var cytokineList = arrayListOf<Double>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            idAnalysis = it.getString(BUNDLE_ANALYSIS_ID).toString()
            idPatient = it.getString(BUNDLE_PATIENT_ID).toString()
        }
        addAnalysisViewModel.getStatusHematological()
        addAnalysisViewModel.getStatusImmune()
        addAnalysisViewModel.getStatusCytokine()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditBinding.inflate(inflater, container, false)
        analysisViewModel.getAnalysisList(idAnalysis)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getListHemotologicalStatus()
        getListImmuneStatus()
        getListCytokine()
        setStatusHematologicalRV()
        setStatusImmuneRV()
        setStatusCytokineRV()
        collapseExpandBlockInput()
        setDateAnalysis()
        setOnClickListener()
    }

    private fun setDateAnalysis() {
        analysisViewModel.analysisList.observe(viewLifecycleOwner, Observer {
            idPatient = it?.patientId.toString()
            binding.etInputDateAnalysis.setText(it?.executionDateStr)
        })
    }

    private fun setOnClickListener() {
        binding.btSave.setOnClickListener {
            if (isAllEditTextFilled(binding.rvUpdateHematological)
                &&isAllEditTextFilled(binding.rvAddImmune)
                &&isAllEditTextFilled(binding.rvAddCytokine)
                && binding.etInputDateAnalysis.text?.isNotEmpty() == true
            ){
                updateHematologicalStatus()
                updateImmuneStatus()
                //saveDateCompletion()
                updateCytokineStatus()
                val transaction = activity?.supportFragmentManager?.beginTransaction()
                transaction?.replace(
                    R.id.main_layout,
                    newInstanceId(idAnalyzes = idAnalysis.toInt(), idPatient = idPatient)
                )?.addToBackStack(null)
                transaction?.commit()
            }
        }
        binding.icExit.setOnClickListener { replaceFragmentMain(DetailsFragment()) }
    }

    private fun launchFragment(fragment: Fragment){
        fragmentManager?.popBackStack()
        fragmentManager?.beginTransaction()
            ?.replace(R.id.main_layout, fragment)
            ?.addToBackStack(null)
            ?.commit()
    }

    private fun updateHematologicalStatus() {
        analysisViewModel.getUpdateHematologicalStatus(
            analysisId = idAnalysis,
            status = textEditTextHematological())
    }

    private fun updateCytokineStatus() {
        analysisViewModel.getUpdateCytokineStatus(
            analysisId = idAnalysis,
            status = textEditTextCytokineStatus())
    }

    private fun updateImmuneStatus() {
        analysisViewModel.getUpdateImmuneStatus(
            analysisId = idAnalysis,
            status = textEditTextImmuneStatus())
    }

    private fun textEditTextCytokineStatus(): CytokineStatus {
        val statusList = getTextFromEditText(binding.rvAddCytokine)
        return CytokineStatus(
            cd3_m_ifny_spontaneous = statusList[0].toDouble(),
            cd3_m_ifny_stimulated = statusList[1].toDouble(),
            cd3_p_il4_spontaneous = statusList[2].toDouble(),
            cd3_p_il4_stimulated = statusList[3].toDouble(),
            cd3_p_il2_spontaneous = statusList[4].toDouble(),
            cd3_p_il2_stimulated = statusList[5].toDouble(),
            cd3_p_tnfa_spontaneous = statusList[6].toDouble(),
            cd3_p_tnfa_stimulated = statusList[7].toDouble(),
            cd3_p_ifny_spontaneous = statusList[8].toDouble(),
            cd3_p_ifny_stimulated = statusList[9].toDouble()
        )
    }

    private fun textEditTextImmuneStatus(): ImmuneStatus {
        val statusList = getTextFromEditText(binding.rvAddImmune)
        return ImmuneStatus(
            monocytes_absorption_activity = statusList[0].toDouble(),
            neutrophil_absorption_activity = statusList[1].toDouble(),
            leukocytes_bactericidal_activity = statusList[2].toDouble(),
            hct_test_spontaneous = statusList[3].toDouble(),
            hct_test_stimulated = statusList[4].toDouble(),
            circulating_immune_complexes = statusList[5].toDouble(),
            lgm = statusList[6].toDouble(),
            lga = statusList[7].toDouble(),
            lgg = statusList[8].toDouble(),
            activated_t_cells_expressing_il2 = statusList[9].toDouble(),
            activated_t_cells = statusList[10].toDouble(),
            tnk_cells = statusList[11].toDouble(),
            cytokine_producing_nk_cells = statusList[12].toDouble(),
            cytolytic_nk_cells = statusList[13].toDouble(),
            common_nk_cells = statusList[14].toDouble(),
            t_cytotoxic_lymphocytes = statusList[15].toDouble(),
            cd3_p_cd4_p_cd3_p_cd8_p_ratio = statusList[16].toDouble(),
            t_helpers = statusList[17].toDouble(),
            common_b_lymphocytes = statusList[18].toDouble(),
            common_t_lymphocytes = statusList[19].toDouble()
        )
    }

    private fun textEditTextHematological() : HematologicalStatus {
        val statusList = getTextFromEditText(binding.rvUpdateHematological)
        return HematologicalStatus(
            pdv = statusList[0].toDouble(),
            pct = statusList[1].toDouble(),
            mpv = statusList[2].toDouble(),
            rdwcv = statusList[3].toDouble(),
            mchc = statusList[4].toDouble(),
            mch = statusList[5].toDouble(),
            mcv = statusList[6].toDouble(),
            rbc = statusList[7].toDouble(),
            plt = statusList[8].toDouble(),
            hct = statusList[9].toDouble(),
            hgb = statusList[10].toDouble(),
            bas = statusList[11].toDouble(),
            eos = statusList[12].toDouble(),
            neu = statusList[13].toDouble(),
            mon = statusList[14].toDouble(),
            lymf = statusList[15].toDouble(),
            wbc = statusList[16].toDouble()
        )
    }

    private fun isAllEditTextFilled(rv: RecyclerView): Boolean {
        var result = true
        for (i in 0 until rv.childCount) {
            val viewHolder = rv.findViewHolderForAdapterPosition(i) as RecyclerView.ViewHolder
            val editText = viewHolder.itemView.findViewById<TextInputEditText>(R.id.et_meaning)
            val meaning_text = viewHolder.itemView.findViewById<TextInputLayout>(R.id.text_input_meaning)

            if (editText.text.toString().isEmpty()) {
                result = false
                meaning_text.error = "error"
            }
        }
        return result
    }

    private fun getListCytokine() {
        analysisViewModel.analysisList.observe(viewLifecycleOwner, Observer { analysis ->
            val cytokineStatus = analysis?.cytokineStatus!!
            cytokineList.add(cytokineStatus.cd3_m_ifny_spontaneous)
            cytokineList.add(cytokineStatus.cd3_m_ifny_stimulated)
            cytokineList.add(cytokineStatus.cd3_p_il4_spontaneous)
            cytokineList.add(cytokineStatus.cd3_p_il4_stimulated)
            cytokineList.add(cytokineStatus.cd3_p_il2_spontaneous)
            cytokineList.add(cytokineStatus.cd3_p_il2_stimulated)
            cytokineList.add(cytokineStatus.cd3_p_tnfa_spontaneous)
            cytokineList.add(cytokineStatus.cd3_p_tnfa_stimulated)
            cytokineList.add(cytokineStatus.cd3_p_ifny_spontaneous)
            cytokineList.add(cytokineStatus.cd3_p_ifny_stimulated)
        })
    }

    private fun setStatusCytokineRV() {
        adapterCytokine = UpdateStatusCytokineAdapter(cytokineList)
        addAnalysisViewModel.statusCytokine.observe(viewLifecycleOwner, Observer {status->
            adapterCytokine.submitList(status)
        })
        binding.rvAddCytokine.adapter = adapterCytokine
        binding.rvAddCytokine.setHasFixedSize(false)
    }

    private fun setStatusImmuneRV() {
        adapterImmune = UpdateStatusImmuneAdapter(immuneList)
        addAnalysisViewModel.statusImmune.observe(viewLifecycleOwner, Observer {status->
            adapterImmune.submitList(status)
        })
        binding.rvAddImmune.adapter = adapterImmune
        binding.rvAddImmune.setHasFixedSize(false)
    }

    private fun setStatusHematologicalRV() {
        adapterHematological = UpdateStatusHematologicalAdapter(hematologicalList)
        addAnalysisViewModel.statusHematological.observe(viewLifecycleOwner, Observer {status->
            adapterHematological.submitList(status)
        })
        binding.rvUpdateHematological.adapter = adapterHematological
        binding.rvUpdateHematological.setHasFixedSize(false)

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

    private fun getTextFromEditText(rv : RecyclerView) : ArrayList<String>{
        var textList = arrayListOf<String>()
        for (i in 0 until rv.childCount) {
            val viewHolder = rv.findViewHolderForAdapterPosition(i) as RecyclerView.ViewHolder
            val editText = viewHolder.itemView.findViewById<TextInputEditText>(R.id.et_meaning)
            textList.add(editText.text.toString())
        }
        return textList
    }

    private fun getListHemotologicalStatus() {
        analysisViewModel.analysisList.observe(viewLifecycleOwner, Observer { analysis ->
            val hematologicalStatus = analysis?.hematologicalStatus!!
            hematologicalList.add(hematologicalStatus.pdv)
            hematologicalList.add(hematologicalStatus.pct)
            hematologicalList.add(hematologicalStatus.mpv)
            hematologicalList.add(hematologicalStatus.rdwcv)
            hematologicalList.add(hematologicalStatus.mchc)
            hematologicalList.add(hematologicalStatus.mch)
            hematologicalList.add(hematologicalStatus.mcv)
            hematologicalList.add(hematologicalStatus.rbc)
            hematologicalList.add(hematologicalStatus.plt)
            hematologicalList.add(hematologicalStatus.hct)
            hematologicalList.add(hematologicalStatus.hgb)
            hematologicalList.add(hematologicalStatus.bas)
            hematologicalList.add(hematologicalStatus.eos)
            hematologicalList.add(hematologicalStatus.neu)
            hematologicalList.add(hematologicalStatus.mon)
            hematologicalList.add(hematologicalStatus.lymf)
            hematologicalList.add(hematologicalStatus.wbc)
            getTextFromEditText(binding.rvUpdateHematological)
        })
    }

    private fun getListImmuneStatus() {
        analysisViewModel.analysisList.observe(viewLifecycleOwner, Observer { analysis ->
            val immune = analysis?.immuneStatus!!
            immuneList.add(immune.monocytes_absorption_activity)
            immuneList.add(immune.neutrophil_absorption_activity)
            immuneList.add(immune.leukocytes_bactericidal_activity)
            immuneList.add(immune.hct_test_spontaneous)
            immuneList.add(immune.hct_test_stimulated)
            immuneList.add(immune.circulating_immune_complexes)
            immuneList.add(immune.lgm)
            immuneList.add(immune.lga)
            immuneList.add(immune.lgg)
            immuneList.add(immune.activated_t_cells_expressing_il2)
            immuneList.add(immune.activated_t_cells)
            immuneList.add(immune.tnk_cells)
            immuneList.add(immune.cytokine_producing_nk_cells)
            immuneList.add(immune.cytolytic_nk_cells)
            immuneList.add(immune.common_nk_cells)
            immuneList.add(immune.t_cytotoxic_lymphocytes)
            immuneList.add(immune.cd3_p_cd4_p_cd3_p_cd8_p_ratio)
            immuneList.add(immune.cd3_p_cd4_p_cd3_p_cd8_p_ratio)
            immuneList.add(immune.t_helpers)
            immuneList.add(immune.common_b_lymphocytes)
            immuneList.add(immune.common_t_lymphocytes)
        })
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
        binding.rvAddImmune.adapter = null
        binding.rvAddCytokine.adapter = null
        binding.rvUpdateHematological.adapter = null
        _binding = null
    }

    companion object {
        private const val BUNDLE_PATIENT_ID = "patient_id"
        private const val BUNDLE_ANALYSIS_ID = "analysis_id"

        fun newInstanceId(idAnalyzes : Int, idPatient : String) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putInt(BUNDLE_ANALYSIS_ID, idAnalyzes)
                    putString(BUNDLE_PATIENT_ID, idPatient)
                }
            }
    }
}
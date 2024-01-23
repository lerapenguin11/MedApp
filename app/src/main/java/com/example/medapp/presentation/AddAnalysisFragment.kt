package com.example.medapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.analysis.Analysis
import com.example.domain.entity.analysis.CytokineStatus
import com.example.domain.entity.analysis.HematologicalStatus
import com.example.domain.entity.analysis.ImmuneStatus
import com.example.medapp.R
import com.example.medapp.databinding.FragmentAddAnalysisBinding
import com.example.medapp.presentation.adapter.StatusCytokineAdapter
import com.example.medapp.presentation.adapter.StatusHematologicalAdapter
import com.example.medapp.presentation.adapter.StatusImmuneAdapter
import com.example.medapp.utilits.replaceFragmentMain
import com.example.medapp.viewmodel.AddAnalysisViewModel
import com.example.medapp.viewmodel.AnalysisViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddAnalysisFragment : Fragment() {
    private var _binding : FragmentAddAnalysisBinding? = null
    private val binding get() = _binding!!
    private var checkDateAnalysis = true
    private var checkHematologicalStatus = true
    private var checkImmuneStatus = true
    private var checkCytokineStatus = true
    private var idPatient: String = ""
    private val addAnalysisViewModel by viewModel<AddAnalysisViewModel>()
    private var idAnalysis : Int = -1
    private var cytokineStatusId : Int? = null
    private var hematologicalStatusId : Int? = null
    private var immuneStatusId : Int? = null
    private val adapterHematological = StatusHematologicalAdapter()
    private val adapterImmune = StatusImmuneAdapter()
    private val adapterCytokine = StatusCytokineAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            arguments?.let {
                idPatient = it.getString(BUNDLE_PATIENT_ID).toString()
            }
        }
        idPatient.let { addAnalysisViewModel.getAddAnalysis(it) }
        addAnalysisViewModel.getStatusHematological()
        addAnalysisViewModel.getStatusImmune()
        addAnalysisViewModel.getStatusCytokine()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddAnalysisBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateDataAnalysis()
        setStatusHematologicalRV()
        setStatusImmuneRV()
        setStatusCytokineRV()
        collapseExpandBlockInput()
        setOnClickListener()
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

    private fun setStatusCytokineRV() {
        addAnalysisViewModel.statusCytokine.observe(viewLifecycleOwner, Observer {status->
            adapterCytokine.submitList(status)
        })
        binding.rvAddCytokine.adapter = adapterCytokine
        binding.rvAddCytokine.setHasFixedSize(false)
    }

    private fun setStatusImmuneRV() {
        addAnalysisViewModel.statusImmune.observe(viewLifecycleOwner, Observer {status->
            adapterImmune.submitList(status)
        })
        binding.rvAddImmune.adapter = adapterImmune
        binding.rvAddImmune.setHasFixedSize(false)
    }

    private fun setStatusHematologicalRV() {
        addAnalysisViewModel.statusHematological.observe(viewLifecycleOwner, Observer {status->
            adapterHematological.submitList(status)
        })
        binding.rvAddHematological.adapter = adapterHematological
        binding.rvAddHematological.setHasFixedSize(false)
    }

    private fun updateDataAnalysis() {
        addAnalysisViewModel.analysis.observe(viewLifecycleOwner, Observer {dataAnalysis->
            dataAnalysis?.id
            if (dataAnalysis != null) {
                idAnalysis = dataAnalysis.id
                cytokineStatusId = dataAnalysis.cytokineStatusId
                hematologicalStatusId = dataAnalysis.hematologicalStatusId
                immuneStatusId = dataAnalysis.immuneStatusId
            }
        })
    }

    private fun setOnClickListener() {
        binding.btSave.setOnClickListener {
            if (isAllEditTextFilled(binding.rvAddHematological)
                &&isAllEditTextFilled(binding.rvAddImmune)
                &&isAllEditTextFilled(binding.rvAddCytokine)
                && binding.etInputDateAnalysis.text?.isNotEmpty() == true
                    ){
                saveHematologicalStatus()
                saveImmuneStatus()
                saveDateCompletion()
                saveCytokineStatus()
                launchFragment(HomeFragment())
            }
        }
        binding.icExit.setOnClickListener { replaceFragmentMain(ChangeInformationFragment()) }
    }

    private fun saveHematologicalStatus() {
        idPatient?.let {
            addAnalysisViewModel.getAddHematologicalStatus(
                patientId = it,
                analysisId = idAnalysis.toString(),
                status = textEditTextHematological()) }
    }

    private fun saveCytokineStatus() {
        idPatient.let {
            addAnalysisViewModel.getCytokineStatus(
                patientId = it,
                analysisId = idAnalysis.toString(),
                status = textEditTextCytokineStatus()) }
    }

    private fun saveImmuneStatus() {
        idPatient.let {
            addAnalysisViewModel.getImmuneStatus(
                patientId = it,
                analysisId = idAnalysis.toString(),
                status = textEditTextImmuneStatus()) }
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

    private fun saveDateCompletion() {
        addAnalysisViewModel.getUpdateAnalysisDate(
            idAnalysis = idAnalysis.toString(),
            idPatient = idPatient,
            date = Analysis(
                id = idAnalysis,
                patientId = idPatient.toInt(),
                executionDateStr = binding.etInputDateAnalysis.text.toString(),
                cytokineStatusId = cytokineStatusId,
                immuneStatusId = immuneStatusId,
                hematologicalStatusId = hematologicalStatusId
            )
        )
    }

    private fun textEditTextHematological() : HematologicalStatus {
        val statusList = getTextFromEditText(binding.rvAddHematological)
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

    private fun getTextFromEditText(rv : RecyclerView) : ArrayList<String>{
        var textList = arrayListOf<String>()
        for (i in 0 until rv.childCount) {
            val viewHolder = rv.findViewHolderForAdapterPosition(i) as RecyclerView.ViewHolder
            val editText = viewHolder.itemView.findViewById<TextInputEditText>(R.id.et_meaning)
            textList.add(editText.text.toString())
        }
        return textList
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
                                 check : Boolean, block : LinearLayout) : Boolean{
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

    private fun launchFragment(fragment: Fragment){
        fragmentManager?.popBackStack()
        fragmentManager?.beginTransaction()
            ?.replace(R.id.main_layout, fragment)
            ?.addToBackStack(null)
            ?.commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.rvAddImmune.adapter = null
        binding.rvAddCytokine.adapter = null
        binding.rvAddHematological.adapter = null
        _binding = null
    }

    companion object {
        private const val BUNDLE_PATIENT_ID = "patient_id"
    }
}
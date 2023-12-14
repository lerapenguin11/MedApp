package com.example.medapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import com.example.domain.entity.analysis.HematologicalStatus
import com.example.medapp.R
import com.example.medapp.databinding.FragmentAddAnalysisBinding
import com.example.medapp.databinding.FragmentAddPatientBinding
import com.example.medapp.utilits.replaceFragmentMain
import com.example.medapp.viewmodel.AddAnalysisViewModel
import com.example.medapp.viewmodel.ChangeInformationViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddAnalysisFragment : Fragment() {
    private var _binding : FragmentAddAnalysisBinding? = null
    private val binding get() = _binding!!
    private var checkDateAnalysis = true
    private var checkHematologicalStatus = true
    private var checkImmuneStatus = true
    private var checkCytokineStatus = true
    private var idPatient: String? = null
    private val addAnalysisViewModel by viewModel<AddAnalysisViewModel>()
    private var idAnalysis = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            arguments?.let {
                idPatient = it.getString(BUNDLE_PATIENT_ID)
            }
        }
        idPatient?.let { addAnalysisViewModel.getAddAnalysis(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddAnalysisBinding.inflate(inflater, container, false)
        addAnalysisViewModel.analysis.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                idAnalysis = it.id
            }
        })
        collapseExpandBlockInput()
        setOnClickListener()
        return binding.root
    }

    private fun setOnClickListener() {
        binding.btSave.setOnClickListener {
            saveHematologicalStatus()
            launchFragment(HomeFragment())
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

    private fun textEditTextHematological() : HematologicalStatus{
        return HematologicalStatus(
            wbc = binding.etInputLeukocyte.text.toString().toDouble(),
            lymf = binding.etInputLymphocytesPercentage.text.toString().toDouble(),
            neu = binding.etInputMonocytesPercentage.text.toString().toDouble(),
            eos = binding.etInputEosinophilsPercentage.text.toString().toDouble(),
            bas = binding.etInputBasophilsPercentage.text.toString().toDouble(),
            hgb = binding.etInputHemoglobin.text.toString().toDouble(),
            hct = binding.etInputHematocrit.text.toString().toDouble(),
            plt = binding.etInputPlatelets.text.toString().toDouble(),
            rbc = binding.etInputErythrocyte.text.toString().toDouble(),
            mvc = binding.etInputMcv.text.toString().toDouble(),
            mch = binding.etInputMch.text.toString().toDouble(),
            rdwcv = binding.etInputRdwCv.text.toString().toDouble(),
            mpv = binding.etInputMpv.text.toString().toDouble(),
            pct = binding.etInputPct.text.toString().toDouble(),
            pdv = binding.etInputPdv.text.toString().toDouble()
        )
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
        _binding = null
    }

    companion object {
        private const val BUNDLE_PATIENT_ID = "patient_id"
    }
}
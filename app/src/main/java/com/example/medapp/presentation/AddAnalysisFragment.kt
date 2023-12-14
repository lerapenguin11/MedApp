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
            wbc = binding.etInputLeukocyte.textDirection.toDouble(),
            lymf = binding.etInputLymphocytesPercentage.textDirection.toDouble(),
            neu = binding.etInputMonocytesPercentage.textDirection.toDouble(),
            eos = binding.etInputEosinophilsPercentage.textDirection.toDouble(),
            bas = binding.etInputBasophilsPercentage.textDirection.toDouble(),
            hgb = binding.etInputHemoglobin.textDirection.toDouble(),
            hct = binding.etInputHematocrit.textDirection.toDouble(),
            plt = binding.etInputPlatelets.textDirection.toDouble(),
            rbc = binding.etInputErythrocyte.textDirection.toDouble(),
            mvc = binding.etInputMcv.textDirection.toDouble(),
            mch = binding.etInputMch.textDirection.toDouble(),
            rdwcv = binding.etInputRdwCv.textDirection.toDouble(),
            mpv = binding.etInputMpv.textDirection.toDouble(),
            pct = binding.etInputPct.textDirection.toDouble(),
            pdv = binding.etInputPdv.textDirection.toDouble()
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
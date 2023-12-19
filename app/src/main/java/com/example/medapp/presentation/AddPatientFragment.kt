package com.example.medapp.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.domain.entity.patient.AddPatient
import com.example.medapp.R
import com.example.medapp.databinding.FragmentAddPatientBinding
import com.example.medapp.viewmodel.AddPatientViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddPatientFragment : Fragment() {
    private var _binding : FragmentAddPatientBinding? = null
    private val binding get() = _binding!!
    private val addPatientViewModel by viewModel<AddPatientViewModel>()
    private var idPatient = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddPatientBinding.inflate(inflater, container, false)
        setOnClickButton()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nameTextListener()
        surnameTextListener()
        patronymicTextListener()
        numberTextListener()
        seriesTextListener()
        dateBirthTextListener()
        diagnosisTextListener()
        errorEditText()
    }

    private fun setOnClickButton() {
        binding.btContinue.setOnClickListener {

            readingDataEditText()
            receivingPatientId()
        }
        binding.icExit.setOnClickListener {launchFragment(HomeFragment())}
    }

    private fun receivingPatientId() {
        addPatientViewModel.patientId.observe(viewLifecycleOwner, Observer {
            if (it?.id != null){
                idPatient = it.id.toString()
                val transaction = activity?.supportFragmentManager?.beginTransaction()
                transaction?.replace(R.id.main_layout, newInstancePatientId(id = idPatient))
                transaction?.commit()
            }
        })
    }

    private fun launchFragment(fragment: Fragment){
        fragmentManager?.popBackStack()
        fragmentManager?.beginTransaction()
            ?.replace(R.id.main_layout, fragment)
            ?.addToBackStack(null)
            ?.commit()
    }

    private fun nameTextListener(){
        binding.etNamePatient.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                addPatientViewModel.resetErrorInputName()
            }
            override fun afterTextChanged(p0: Editable?) {}
        })
    }

    private fun surnameTextListener(){
        binding.etSurnamePatient.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                addPatientViewModel.resetErrorInputSurname()
            }
            override fun afterTextChanged(p0: Editable?) {}
        })
    }

    private fun patronymicTextListener(){
        binding.etPatronymicPatient.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                addPatientViewModel.resetErrorInputPatronymic()
            }
            override fun afterTextChanged(p0: Editable?) {}
        })
    }

    private fun numberTextListener(){
        binding.etPassportNumber.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                addPatientViewModel.resetErrorInputNumber()
            }
            override fun afterTextChanged(p0: Editable?) {}
        })
    }

    private fun seriesTextListener(){
        binding.etPassportSeries.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                addPatientViewModel.resetErrorInputSeries()
            }
            override fun afterTextChanged(p0: Editable?) {}
        })
    }

    private fun diagnosisTextListener(){
        binding.etDiagnosis.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                addPatientViewModel.resetErrorInputDiagnosis()
            }
            override fun afterTextChanged(p0: Editable?) {}
        })
    }

    private fun dateBirthTextListener(){
        binding.etDateOfBirth.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                addPatientViewModel.resetErrorInputDateBirth()
            }
            override fun afterTextChanged(p0: Editable?) {}
        })
    }

    private fun readingDataEditText() {
        val birthDateStr = binding.etDateOfBirth.text.toString()
        val diagnosis = binding.etDiagnosis.text.toString()
        val docNumber = binding.etPassportNumber.text.toString()
        val docSeries = binding.etPassportSeries.text.toString()
        val firstName = binding.etNamePatient.text.toString()
        val lastName = binding.etSurnamePatient.text.toString()
        val middleName = binding.etPatronymicPatient.text.toString()
        if (addPatientViewModel.validateInput(
                firstName, lastName, middleName,
                docNumber, docSeries, diagnosis,
                birthDateStr)){
            addPatientViewModel.addPatient(
                AddPatient(
                    birthDateStr = binding.etDateOfBirth.text.toString(),
                    diagnosis = binding.etDiagnosis.text.toString(),
                    docNumber = binding.etPassportNumber.text.toString(),
                    docSeries = binding.etPassportSeries.text.toString(),
                    firstName = binding.etNamePatient.text.toString(),
                    lastName = binding.etSurnamePatient.text.toString(),
                    middleName = binding.etPatronymicPatient.text.toString())
            )
        }
    }

    private fun errorEditText() {
        addPatientViewModel.errorInputName.observe(viewLifecycleOwner){
            val message = if (it){
                getString(R.string.error_input)
            }else{
                null
            }
            binding.textInputNamePatient.error = message
        }
        addPatientViewModel.errorInputSurname.observe(viewLifecycleOwner){
            val message = if (it){
                getString(R.string.error_input)
            }else{
                null
            }
            binding.textInputSurnamePatient.error = message
        }
        addPatientViewModel.errorInputPatronymic.observe(viewLifecycleOwner){
            val message = if (it){
                getString(R.string.error_input)
            }else{
                null
            }
            binding.textInputPatronymicPatient.error = message
        }
        addPatientViewModel.errorInputNumber.observe(viewLifecycleOwner){
            val message = if (it){
                getString(R.string.error_input)
            }else{
                null
            }
            binding.textInputPassportNumber.error = message
        }
        addPatientViewModel.errorInputSeries.observe(viewLifecycleOwner){
            val message = if (it){
                getString(R.string.error_input)
            }else{
                null
            }
            binding.textInputPassportSeries.error = message
        }
        addPatientViewModel.errorInputDateBirth.observe(viewLifecycleOwner){
            val message = if (it){
                getString(R.string.error_input)
            }else{
                null
            }
            binding.textInputDateOfBirth.error = message
        }
        addPatientViewModel.errorInputDiagnosis.observe(viewLifecycleOwner){
            val message = if (it){
                getString(R.string.error_input)
            }else{
                null
            }
            binding.textInputDiagnosis.error = message
        }
    }

    companion object {
        private const val BUNDLE_PATIENT_ID = "patient_id"

        fun newInstancePatientId(id : String) =
            ChangeInformationFragment().apply {
                arguments = Bundle().apply {
                    putString(BUNDLE_PATIENT_ID, id)
                }
            }
    }
}
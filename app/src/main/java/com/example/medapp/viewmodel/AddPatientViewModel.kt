package com.example.medapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.common.ResultMed
import com.example.domain.entity.patient.AddPatient
import com.example.domain.entity.patient.NewPatientId
import com.example.domain.usecase.patient.GetAddPatientUseCase
import kotlinx.coroutines.launch

class AddPatientViewModel(
    private val getAddPatientUseCase: GetAddPatientUseCase
) : ViewModel()
{
    private val _patient = MutableLiveData<NewPatientId?>()
    val patientId: LiveData<NewPatientId?> get() = _patient

    private val _errorPatient = MutableLiveData<String>()
    val errorPatient: LiveData<String> = _errorPatient

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName : LiveData<Boolean>
        get() = _errorInputName

    private val _errorInputSurname = MutableLiveData<Boolean>()
    val errorInputSurname : LiveData<Boolean>
        get() = _errorInputSurname

    private val _errorInputPatronymic = MutableLiveData<Boolean>()
    val errorInputPatronymic : LiveData<Boolean>
        get() = _errorInputPatronymic

    private val _errorInputNumber = MutableLiveData<Boolean>()
    val errorInputNumber : LiveData<Boolean>
        get() = _errorInputNumber

    private val _errorInputSeries = MutableLiveData<Boolean>()
    val errorInputSeries : LiveData<Boolean>
        get() = _errorInputSeries

    private val _errorInputDiagnosis = MutableLiveData<Boolean>()
    val errorInputDiagnosis : LiveData<Boolean>
        get() = _errorInputDiagnosis

    private val _errorInputDateBirth = MutableLiveData<Boolean>()
    val errorInputDateBirth : LiveData<Boolean>
        get() = _errorInputDateBirth

    fun addPatient(patient: AddPatient) {
        viewModelScope.launch {
            when (val response = getAddPatientUseCase.invoke(patient)) {
                is ResultMed.Success -> {
                    _patient.value = response.data
                }

                is ResultMed.Error -> {
                    _errorPatient.value = response.exception.message
                }
            }
        }
    }

    fun validateInput(
        inputName : String?, inputSurname : String?,
        inputPatronymic : String?, inputNumber : String?,
        inputSeries : String?, inputDiagnosis : String?, inputDate : String?) : Boolean{
        var result = true
        val name = parseName(inputName)
        val surname = parseSurname(inputSurname)
        val patronymic = parsePatronymic(inputPatronymic)
        val number = parseNumber(inputNumber)
        val series = parseSeries(inputSeries)
        val diagnosis = parseDiagnosis(inputDiagnosis)
        val date = parseDate(inputDate)
        if (name.isBlank()){
            _errorInputName.value = true
            result =  false
        }
        if (surname.isBlank()){
            _errorInputSurname.value = true
            result = false
        }
        if (patronymic.isBlank()){
            _errorInputPatronymic.value = true
            result = false
        }
        if (number.isBlank()){
            _errorInputNumber.value = true
            result = false
        }
        if (series.isBlank()){
            _errorInputSeries.value = true
            result = false
        }
        if (diagnosis.isBlank()){
            _errorInputDiagnosis.value = true
            result = false
        }
        if (date.isBlank()){
            _errorInputDateBirth.value = true
            result = false
        }
        return result
    }

    private fun parseName(inputName : String?) : String{
        return inputName?.trim() ?: ""
    }

    private fun parseSurname(inputSurname : String?) : String{
        return inputSurname?.trim() ?: ""
    }

    private fun parsePatronymic(inputPatronymic : String?) : String{
        return inputPatronymic?.trim() ?: ""
    }

    private fun parseNumber(inputNumber : String?) : String{
        return inputNumber?.trim() ?: ""
    }

    private fun parseSeries(inputSeries : String?) : String{
        return inputSeries?.trim() ?: ""
    }

    private fun parseDate(inputDate : String?) : String{
        return inputDate?.trim() ?: ""
    }

    private fun parseDiagnosis(inputDiagnosis : String?) : String{
        return inputDiagnosis?.trim() ?: ""
    }

    fun resetErrorInputName(){
        _errorInputName.value = false
    }

    fun resetErrorInputSurname(){
        _errorInputSurname.value = false
    }

    fun resetErrorInputPatronymic(){
        _errorInputPatronymic.value = false
    }

    fun resetErrorInputNumber(){
        _errorInputNumber.value = false
    }

    fun resetErrorInputSeries(){
        _errorInputSeries.value = false
    }

    fun resetErrorInputDiagnosis(){
        _errorInputDiagnosis.value = false
    }

    fun resetErrorInputDateBirth(){
        _errorInputDateBirth.value = false
    }
}
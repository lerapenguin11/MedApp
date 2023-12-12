package repository.patientRepository

import com.example.domain.common.ResultMed
import com.example.domain.entity.AddPatient
import com.example.domain.entity.Patients

interface PatientRemoteSource{

    suspend fun getPatientList(): ResultMed<List<Patients>>

    suspend fun getAddPatient(patient : AddPatient)
}
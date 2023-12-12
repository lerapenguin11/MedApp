package repository.patientRepository

import com.example.domain.common.ResultMed
import com.example.domain.entity.Patient

interface PatientRemoteSource{

    suspend fun getPatientList(): ResultMed<List<Patient>>
}
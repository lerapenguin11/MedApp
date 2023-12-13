package repository.patientRepository

import com.example.domain.common.ResultMed
import com.example.domain.entity.AddPatient
import com.example.domain.entity.NewPatientId
import com.example.domain.entity.Patients

interface PatientRemoteSource{

    suspend fun getPatientList(): ResultMed<List<Patients>>

    suspend fun getAddPatient(patient : AddPatient) : ResultMed<NewPatientId?>

    suspend fun getNewPatientId(id : String) : ResultMed<NewPatientId>
}
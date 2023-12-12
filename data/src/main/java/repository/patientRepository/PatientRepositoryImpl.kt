package repository.patientRepository

import com.example.domain.common.ResultMed
import com.example.domain.entity.AddPatient
import com.example.domain.entity.Patients
import com.example.domain.repository.PatientRepository

class PatientRepositoryImpl(
    private val remoteSource : PatientRemoteSource
) : PatientRepository{
    override suspend fun getPatientList(): ResultMed<List<Patients>> {
        return remoteSource.getPatientList()
    }

    override suspend fun getAddPatient(patient: AddPatient) {
        return remoteSource.getAddPatient(patient)
    }
}
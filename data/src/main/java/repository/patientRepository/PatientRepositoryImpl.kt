package repository.patientRepository

import com.example.domain.common.ResultMed
import com.example.domain.entity.patient.AddPatient
import com.example.domain.entity.patient.NewPatientId
import com.example.domain.entity.patient.Patients
import com.example.domain.repository.PatientRepository

class PatientRepositoryImpl(
    private val remoteSource : PatientRemoteSource
) : PatientRepository{
    override suspend fun getPatientList(): ResultMed<List<Patients>> {
        return remoteSource.getPatientList()
    }

    override suspend fun getAddPatient(patient: AddPatient): ResultMed<NewPatientId?> {
        return remoteSource.getAddPatient(patient = patient)
    }

    override suspend fun getNewPatientId(id: String): ResultMed<NewPatientId> {
        return remoteSource.getNewPatientId(id = id)
    }
}
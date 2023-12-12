package repository.patientRepository

import com.example.domain.common.ResultMed
import com.example.domain.entity.Patient
import com.example.domain.repository.PatientRepository

class PatientRepositoryImpl(
    private val remoteSource : PatientRemoteSource
) : PatientRepository{
    override suspend fun getPatientList(): ResultMed<List<Patient>> {
        return remoteSource.getPatientList()
    }
}
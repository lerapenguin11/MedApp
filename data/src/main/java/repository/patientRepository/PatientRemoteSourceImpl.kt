package repository.patientRepository

import com.example.data.api.MedApi
import com.example.data.mappers.PatientApiResponseMapper
import com.example.domain.common.ResultMed
import com.example.domain.entity.patient.AddPatient
import com.example.domain.entity.patient.NewPatientId
import com.example.domain.entity.patient.Patients
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PatientRemoteSourceImpl(
    private val service : MedApi,
    private val mapper : PatientApiResponseMapper
) : PatientRemoteSource {
    override suspend fun getPatientList(): ResultMed<List<Patients>> =
        withContext(Dispatchers.IO) {
            try {
                val response = service.getAllPatient()
                if (response.isSuccessful) {

                    return@withContext ResultMed.Success(mapper.toPatientList(response.body()!!))
                } else {
                    return@withContext ResultMed.Error(Exception(response.message()))
                }
            } catch (e: Exception) {
                return@withContext ResultMed.Error(e)
            }
        }

    override suspend fun getAddPatient(patient: AddPatient) : ResultMed<NewPatientId?> =
        withContext(Dispatchers.IO) {
            try {
                val response = service.getAddPatient(mapper.toPatientAdd(patient))
                if (response.isSuccessful) {

                    return@withContext ResultMed.Success(response.body()
                        ?.let { mapper.toNewPatientId(it) })
                } else {
                    return@withContext ResultMed.Error(Exception(response.message()))
                }
            } catch (e: Exception) {
                return@withContext ResultMed.Error(e)
            }
        }

    override suspend fun getNewPatientId(id: String): ResultMed<NewPatientId> =
        withContext(Dispatchers.IO) {
            try {
                val response = service.getPatientId(patientId = id)
                if (response.isSuccessful) {

                    return@withContext ResultMed.Success(mapper.toNewPatientId(response.body()!!))
                } else {
                    return@withContext ResultMed.Error(Exception(response.message()))
                }
            } catch (e: Exception) {
                return@withContext ResultMed.Error(e)
            }
        }
}
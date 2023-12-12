package repository.patientRepository

import com.example.data.api.MedApi
import com.example.data.mappers.PatientApiResponseMapper
import com.example.domain.common.ResultMed
import com.example.domain.entity.Patient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PatientRemoteSourceImpl(
    private val service : MedApi,
    private val mapper : PatientApiResponseMapper
) : PatientRemoteSource {
    override suspend fun getPatientList(): ResultMed<List<Patient>> =
        withContext(Dispatchers.IO) {
            try {
                val response = service.getPatient()
                if (response.isSuccessful) {

                    return@withContext ResultMed.Success(mapper.toPatientList(response.body()!!))
                } else {
                    return@withContext ResultMed.Error(Exception(response.message()))
                }
            } catch (e: Exception) {
                return@withContext ResultMed.Error(e)
            }
        }
}
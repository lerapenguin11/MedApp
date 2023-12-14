package repository.analysisRepository

import com.example.data.api.MedApi
import com.example.data.mappers.AnalysisApiResponseMapper
import com.example.domain.common.ResultMed
import com.example.domain.entity.Analysis
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AnalysisRemoteSourceImpl(
    private val service : MedApi,
    private val mapper : AnalysisApiResponseMapper
) : AnalysisRemoteSource{
    override suspend fun getNewAnalysis(idPatient: String): ResultMed<Analysis> =
        withContext(Dispatchers.IO) {
            try {
                val response = service.getNewAnalysis(patientId = idPatient)
                if (response.isSuccessful) {

                    return@withContext ResultMed.Success(mapper.toAnalysisModel(response.body()!!))
                } else {
                    return@withContext ResultMed.Error(Exception(response.message()))
                }
            } catch (e: Exception) {
                return@withContext ResultMed.Error(e)
            }
        }
}
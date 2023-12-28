package repository.analysisRepository

import com.example.data.api.MedApi
import com.example.data.mappers.AnalysisApiResponseMapper
import com.example.domain.common.ResultMed
import com.example.domain.entity.analysis.patientAnalysis.PatientAnalysisList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AnalysisRemoteSourceImpl(
    private val service : MedApi,
    private val mapper : AnalysisApiResponseMapper
) : AnalysisRemoteSource
{
    override suspend fun getPatientAnalysisList(patientId: String): ResultMed<List<PatientAnalysisList>> =
        withContext(Dispatchers.IO) {
            try {
                val response = service.getPatientAnalysisList(patientId = patientId)
                if (response.isSuccessful) {

                    return@withContext ResultMed.Success(mapper.toAnalysisListModel(response.body()!!))
                } else {
                    return@withContext ResultMed.Error(Exception(response.message()))
                }
            } catch (e: Exception) {
                return@withContext ResultMed.Error(e)
            }
        }
}
package repository.analysisRepository

import com.example.data.api.MedApi
import com.example.data.mappers.AnalysisApiResponseMapper
import com.example.domain.common.ResultMed
import com.example.domain.entity.analysis.Analysis
import com.example.domain.entity.analysis.HematologicalStatus
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

    override suspend fun getAddHematologicalStatus(
        idPatient: String,
        idAnalysis: String, status : HematologicalStatus
    ): ResultMed<HematologicalStatus> =
        withContext(Dispatchers.IO) {
            try {
                val response = service.getAddHematologicalStatus(patientId = idPatient,
                                                            analysisId = idAnalysis,
                                        status = mapper.toHematologicalStatus(status))
                if (response.isSuccessful) {

                    return@withContext ResultMed.Success(mapper.toHematologicalStatusModel(response.body()!!))
                } else {
                    return@withContext ResultMed.Error(Exception(response.message()))
                }
            } catch (e: Exception) {
                return@withContext ResultMed.Error(e)
            }
        }

    override suspend fun getUpdateAnalysisDate(date: String, idPatient : String,
                                               idAnalysis : String): ResultMed<Analysis> =
        withContext(Dispatchers.IO) {
            try {
                val response = service.getUpdateAnalysisDate(date = date, patientId = idPatient,
                    analysisId = idAnalysis)
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
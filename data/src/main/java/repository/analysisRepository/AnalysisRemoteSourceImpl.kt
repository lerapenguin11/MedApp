package repository.analysisRepository

import com.example.data.api.MedApi
import com.example.data.mappers.AnalysisApiResponseMapper
import com.example.domain.common.ResultMed
import com.example.domain.entity.analysis.Analysis
import com.example.domain.entity.analysis.AnalysisList
import com.example.domain.entity.analysis.HematologicalStatus
import com.example.domain.entity.analysis.ImmuneStatus
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

    override suspend fun getUpdateAnalysisDate(date: Analysis, idPatient : String,
                                               idAnalysis : String): ResultMed<Analysis> =
        withContext(Dispatchers.IO) {
            try {
                val response = service.getUpdateAnalysisDate(date = mapper.toAnalysis(date), patientId = idPatient,
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

    override suspend fun getAnalysisList(patientId: String): ResultMed<List<AnalysisList>> =
        withContext(Dispatchers.IO) {
            try {
                val response = service.getAnalysisList(patientId = patientId)
                if (response.isSuccessful) {

                    return@withContext ResultMed.Success(mapper.toAnalysisListModel(response.body()!!))
                } else {
                    return@withContext ResultMed.Error(Exception(response.message()))
                }
            } catch (e: Exception) {
                return@withContext ResultMed.Error(e)
            }
        }

    override suspend fun getAddImmuneStatus(
        idPatient: String,
        idAnalysis: String,
        status: ImmuneStatus
    ): ResultMed<ImmuneStatus> =
        withContext(Dispatchers.IO) {
            try {
                val response = service.getAddImmuneStatus(patientId = idPatient,
                    analysisId = idAnalysis,
                    status = mapper.toImmuneStatus(status))
                if (response.isSuccessful) {

                    return@withContext ResultMed.Success(mapper.toImmuneStatusModel(response.body()!!))
                } else {
                    return@withContext ResultMed.Error(Exception(response.message()))
                }
            } catch (e: Exception) {
                return@withContext ResultMed.Error(e)
            }
        }
}
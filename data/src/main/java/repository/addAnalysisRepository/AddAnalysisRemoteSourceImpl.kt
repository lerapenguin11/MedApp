package repository.addAnalysisRepository

import com.example.data.api.MedApi
import com.example.data.mappers.AddAnalysisApiResponseMapper
import com.example.domain.common.ResultMed
import com.example.domain.entity.analysis.Analysis
import com.example.domain.entity.analysis.AnalysisList
import com.example.domain.entity.analysis.CytokineStatus
import com.example.domain.entity.analysis.HematologicalStatus
import com.example.domain.entity.analysis.ImmuneStatus
import com.example.domain.entity.analysis.StatusList
import com.example.domain.entity.analysis.patientAnalysis.PatientAnalysisList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AddAnalysisRemoteSourceImpl(
    private val service : MedApi,
    private val mapper : AddAnalysisApiResponseMapper
) : AddAnalysisRemoteSource{
    override suspend fun getNewAnalysis(idPatient: String): ResultMed<Analysis> =
        withContext(Dispatchers.IO) {
            try {
                val response = service.getNewAnalysis(
                    patientId = mapper.toPatientIdRequest(idPatient))
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
                val response = service.getAddHematologicalStatus(
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
                val response = service.getUpdateAnalysisDate(date = mapper.toAnalysis(date),
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
                val response = service.getAddImmuneStatus(
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

    override suspend fun getAddCytokineStatus(
        idPatient: String,
        idAnalysis: String,
        status: CytokineStatus
    ): ResultMed<CytokineStatus> =
        withContext(Dispatchers.IO) {
            try {
                val response = service.getAddCytokinStatus(
                    analysisId = idAnalysis,
                    status = mapper.toCytokineStatus(status))
                if (response.isSuccessful) {

                    return@withContext ResultMed.Success(mapper.toCytokineStatusModel(response.body()!!))
                } else {
                    return@withContext ResultMed.Error(Exception(response.message()))
                }
            } catch (e: Exception) {
                return@withContext ResultMed.Error(e)
            }
        }

    override suspend fun getValuesHematologicalStatus(): ResultMed<List<StatusList>> =
        withContext(Dispatchers.IO) {
            try {
                val response = service.getValuesHematologicalStatus()
                if (response.isSuccessful) {

                    return@withContext ResultMed.Success(mapper.toValuesStatus(response.body()!!))
                } else {
                    return@withContext ResultMed.Error(Exception(response.message()))
                }
            } catch (e: Exception) {
                return@withContext ResultMed.Error(e)
            }
        }

    override suspend fun getValuesImmuneStatus(): ResultMed<List<StatusList>> =
        withContext(Dispatchers.IO) {
            try {
                val response = service.getValuesImmuneStatus()
                if (response.isSuccessful) {

                    return@withContext ResultMed.Success(mapper.toValuesStatus(response.body()!!))
                } else {
                    return@withContext ResultMed.Error(Exception(response.message()))
                }
            } catch (e: Exception) {
                return@withContext ResultMed.Error(e)
            }
        }

    override suspend fun getValuesCytokineStatus(): ResultMed<List<StatusList>> =
        withContext(Dispatchers.IO) {
            try {
                val response = service.getValuesCytokinStatus()
                if (response.isSuccessful) {

                    return@withContext ResultMed.Success(mapper.toValuesStatus(response.body()!!))
                } else {
                    return@withContext ResultMed.Error(Exception(response.message()))
                }
            } catch (e: Exception) {
                return@withContext ResultMed.Error(e)
            }
        }

    override suspend fun updateHematologicalStatus(
        idAnalysis: String,
        status: HematologicalStatus
    ): ResultMed<HematologicalStatus> =
        withContext(Dispatchers.IO) {
            try {
                val response = service.updateHematological(analysisId = idAnalysis,
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

    override suspend fun updateImmuneStatus(
        idAnalysis: String,
        status: ImmuneStatus
    ): ResultMed<ImmuneStatus> =
        withContext(Dispatchers.IO) {
            try {
                val response = service.updateImmune(analysisId = idAnalysis,
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

    override suspend fun updateCytokineStatus(
        idAnalysis: String,
        status: CytokineStatus
    ): ResultMed<CytokineStatus> =
        withContext(Dispatchers.IO) {
            try {
                val response = service.updateCytokine(analysisId = idAnalysis,
                    status = mapper.toCytokineStatus(status))
                if (response.isSuccessful) {

                    return@withContext ResultMed.Success(mapper.toCytokineStatusModel(response.body()!!))
                } else {
                    return@withContext ResultMed.Error(Exception(response.message()))
                }
            } catch (e: Exception) {
                return@withContext ResultMed.Error(e)
            }
        }

    override suspend fun getAnalysisId(analysisId: String): ResultMed<PatientAnalysisList> =
        withContext(Dispatchers.IO) {
            try {
                val response = service.getAnalysisId(analysisId = analysisId)
                if (response.isSuccessful) {

                    return@withContext ResultMed.Success(mapper.toAnalysisId(response.body()!!))
                } else {
                    return@withContext ResultMed.Error(Exception(response.message()))
                }
            } catch (e: Exception) {
                return@withContext ResultMed.Error(e)
            }
        }
}
package repository.addAnalysisRepository

import com.example.domain.common.ResultMed
import com.example.domain.entity.analysis.Analysis
import com.example.domain.entity.analysis.AnalysisList
import com.example.domain.entity.analysis.CytokineStatus
import com.example.domain.entity.analysis.HematologicalStatus
import com.example.domain.entity.analysis.ImmuneStatus
import com.example.domain.entity.analysis.StatusList
import com.example.domain.entity.analysis.patientAnalysis.PatientAnalysisList
import com.example.domain.repository.AddAnalysisRepository

class AddAnalysisRepositoryImpl(
    private val remoteSource : AddAnalysisRemoteSource
) : AddAnalysisRepository{
    override suspend fun getNewAnalysis(idPatient: String): ResultMed<Analysis> {
        return remoteSource.getNewAnalysis(idPatient = idPatient)
    }

    override suspend fun getAddHematologicalStatus(
        idPatient: String,
        idAnalysis: String, status : HematologicalStatus
    ): ResultMed<HematologicalStatus> {
        return remoteSource.getAddHematologicalStatus(idPatient = idPatient,
        idAnalysis = idAnalysis, status = status)
    }

    override suspend fun getAddImmuneStatus(
        idPatient: String,
        idAnalysis: String,
        status: ImmuneStatus
    ): ResultMed<ImmuneStatus> {
        return remoteSource.getAddImmuneStatus(
            idPatient = idPatient,
            idAnalysis = idAnalysis,
            status = status)
    }

    override suspend fun getAddCytokineStatus(
        idPatient: String,
        idAnalysis: String,
        status: CytokineStatus
    ): ResultMed<CytokineStatus> {
        return remoteSource.getAddCytokineStatus(
            idPatient = idPatient,
            idAnalysis = idAnalysis,
            status = status)
    }

    override suspend fun getUpdateAnalysisDate(date: Analysis, idPatient : String,
                                               idAnalysis : String): ResultMed<Analysis> {
        return remoteSource.getUpdateAnalysisDate(date = date,
            idAnalysis = idAnalysis, idPatient = idPatient)
    }

    override suspend fun getAnalysisList(patientId: String): ResultMed<List<AnalysisList>> {
        return remoteSource.getAnalysisList(patientId = patientId)
    }

    override suspend fun getValuesHematologicalStatus(): ResultMed<List<StatusList>> {
        return remoteSource.getValuesHematologicalStatus()
    }

    override suspend fun getValuesImmuneStatus(): ResultMed<List<StatusList>> {
        return remoteSource.getValuesImmuneStatus()
    }

    override suspend fun getValuesCytokineStatus(): ResultMed<List<StatusList>> {
        return remoteSource.getValuesCytokineStatus()
    }

    override suspend fun updateHematologicalStatus(
        idAnalysis: String,
        status: HematologicalStatus
    ): ResultMed<HematologicalStatus> {
        return remoteSource.updateHematologicalStatus(idAnalysis = idAnalysis, status = status)
    }

    override suspend fun updateImmuneStatus(
        idAnalysis: String,
        status: ImmuneStatus
    ): ResultMed<ImmuneStatus> {
        return remoteSource.updateImmuneStatus(idAnalysis = idAnalysis, status = status)
    }

    override suspend fun updateCytokineStatus(
        idAnalysis: String,
        status: CytokineStatus
    ): ResultMed<CytokineStatus> {
        return remoteSource.updateCytokineStatus(idAnalysis = idAnalysis, status = status)
    }

    override suspend fun getAnalysisId(analysisId: String): ResultMed<PatientAnalysisList> {
        return remoteSource.getAnalysisId(analysisId)
    }
}
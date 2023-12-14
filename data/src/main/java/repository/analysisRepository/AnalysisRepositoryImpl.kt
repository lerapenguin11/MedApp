package repository.analysisRepository

import com.example.domain.common.ResultMed
import com.example.domain.entity.analysis.Analysis
import com.example.domain.entity.analysis.HematologicalStatus
import com.example.domain.repository.AnalysisRepository

class AnalysisRepositoryImpl(
    private val remoteSource : AnalysisRemoteSource
) : AnalysisRepository{
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
}
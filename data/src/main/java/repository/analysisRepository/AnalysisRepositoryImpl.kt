package repository.analysisRepository

import com.example.domain.common.ResultMed
import com.example.domain.entity.Analysis
import com.example.domain.repository.AnalysisRepository

class AnalysisRepositoryImpl(
    private val remoteSource : AnalysisRemoteSource
) : AnalysisRepository{
    override suspend fun getNewAnalysis(idPatient: String): ResultMed<Analysis> {
        return remoteSource.getNewAnalysis(idPatient = idPatient)
    }
}
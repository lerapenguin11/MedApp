package repository.analysisRepository

import com.example.domain.common.ResultMed
import com.example.domain.entity.analysis.patientAnalysis.PatientAnalysisList
import com.example.domain.repository.AnalysisRepository

class AnalysisRepositoryImpl(
    private val remoteSource: AnalysisRemoteSource
) : AnalysisRepository
{
    override suspend fun getPatientAnalysisList(patientId: String): ResultMed<List<PatientAnalysisList>> {
        return remoteSource.getPatientAnalysisList(patientId)
    }
}
package repository.analysisRepository

import com.example.domain.common.ResultMed
import com.example.domain.entity.analysis.patientAnalysis.PatientAnalysisList

interface AnalysisRemoteSource
{
    suspend fun getPatientAnalysisList(patientId : String) : ResultMed<List<PatientAnalysisList>>
}
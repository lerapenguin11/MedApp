package repository.analysisRepository

import com.example.domain.common.ResultMed
import com.example.domain.entity.analysis.Analysis
import com.example.domain.entity.analysis.HematologicalStatus

interface AnalysisRemoteSource {
    suspend fun getNewAnalysis(idPatient : String) : ResultMed<Analysis>
    suspend fun getAddHematologicalStatus
                (idPatient : String,
                 idAnalysis : String,
                 status : HematologicalStatus) : ResultMed<HematologicalStatus>
    suspend fun getUpdateAnalysisDate(
        date : String,
        idPatient : String,
        idAnalysis : String) : ResultMed<Analysis>
}
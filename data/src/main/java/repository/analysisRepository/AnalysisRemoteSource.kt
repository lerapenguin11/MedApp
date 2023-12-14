package repository.analysisRepository

import com.example.domain.common.ResultMed
import com.example.domain.entity.Analysis

interface AnalysisRemoteSource {
    suspend fun getNewAnalysis(idPatient : String) : ResultMed<Analysis>
}
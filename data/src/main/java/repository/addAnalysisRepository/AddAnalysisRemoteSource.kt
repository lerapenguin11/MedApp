package repository.addAnalysisRepository

import com.example.domain.common.ResultMed
import com.example.domain.entity.analysis.Analysis
import com.example.domain.entity.analysis.AnalysisList
import com.example.domain.entity.analysis.CytokineStatus
import com.example.domain.entity.analysis.HematologicalStatus
import com.example.domain.entity.analysis.ImmuneStatus
import com.example.domain.entity.analysis.StatusList
import com.example.domain.entity.analysis.patientAnalysis.PatientAnalysisList

interface AddAnalysisRemoteSource {
    suspend fun getNewAnalysis(idPatient : String) : ResultMed<Analysis>
    suspend fun getAddHematologicalStatus
                (idPatient : String,
                 idAnalysis : String,
                 status : HematologicalStatus) : ResultMed<HematologicalStatus>
    suspend fun getUpdateAnalysisDate(
        date : Analysis,
        idPatient : String,
        idAnalysis : String) : ResultMed<Analysis>

    suspend fun getAnalysisList(patientId : String): ResultMed<List<AnalysisList>>

    suspend fun getAddImmuneStatus
                (idPatient : String,
                 idAnalysis : String,
                 status : ImmuneStatus
    ) : ResultMed<ImmuneStatus>
    suspend fun getAddCytokineStatus
                (idPatient : String,
                 idAnalysis : String,
                 status : CytokineStatus
    ) : ResultMed<CytokineStatus>

    suspend fun getValuesHematologicalStatus() : ResultMed<List<StatusList>>

    suspend fun getValuesImmuneStatus() : ResultMed<List<StatusList>>

    suspend fun getValuesCytokineStatus() : ResultMed<List<StatusList>>

    suspend fun updateHematologicalStatus
                (idAnalysis : String,
                 status : HematologicalStatus)
            : ResultMed<HematologicalStatus>

    suspend fun updateImmuneStatus
                (idAnalysis : String,
                 status : ImmuneStatus)
            : ResultMed<ImmuneStatus>
    suspend fun updateCytokineStatus
                (idAnalysis : String,
                 status : CytokineStatus)
            : ResultMed<CytokineStatus>

    suspend fun getAnalysisId(
        analysisId : String
    ) : ResultMed<PatientAnalysisList>
}
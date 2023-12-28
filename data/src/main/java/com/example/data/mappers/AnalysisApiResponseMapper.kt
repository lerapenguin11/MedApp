package com.example.data.mappers

import com.example.data.api.model.analysis.CytokineStatus
import com.example.data.api.model.analysis.HematologicalStatus
import com.example.data.api.model.analysis.ImmuneStatus
import com.example.data.api.model.analysis.PatientAnalysisListModel
import com.example.domain.entity.analysis.patientAnalysis.Cytokine
import com.example.domain.entity.analysis.patientAnalysis.Hematological
import com.example.domain.entity.analysis.patientAnalysis.Immune
import com.example.domain.entity.analysis.patientAnalysis.PatientAnalysisList

@Suppress("CAST_NEVER_SUCCEEDS")
class AnalysisApiResponseMapper {
    fun toAnalysisListModel(response : PatientAnalysisListModel): List<PatientAnalysisList> {
        var list = arrayListOf<PatientAnalysisList>()
        for (i in response){
            val createdAt = i.createdAt
            val cytokineStatus= i.cytokineStatus
            val cytokineStatusId = i.cytokineStatusId
            val deletedAt = i.deletedAt
            val executionDateStr = i.executionDateStr
            val hematologicalStatus = i.hematologicalStatus
            val hematologicalStatusId = i.hematologicalStatusId
            val id = i.id
            val immuneStatus = i.immuneStatus
            val immuneStatusId = i.immuneStatusId
            val patientId = i.patientId
            val updatedAt = i.updatedAt
            val analysis = PatientAnalysisList(
                createdAt = createdAt,
                cytokineStatus = toCytokineStatus(cytokineStatus),
                cytokineStatusId = cytokineStatusId,
                deletedAt = deletedAt,
                executionDateStr = executionDateStr,
                hematologicalStatus = toHematological(hematologicalStatus),
                hematologicalStatusId = hematologicalStatusId,
                id = id,
                immuneStatus = toImmune(immuneStatus),
                immuneStatusId = immuneStatusId,
                patientId = patientId,
                updatedAt = updatedAt
            )
            list.add(analysis)
        }
        return list
    }

    private fun toImmune(status: ImmuneStatus): Immune {
        return Immune(
            activated_t_cells = status.activated_t_cells,
            activated_t_cells_expressing_il2 = status.activated_t_cells_expressing_il2,
            cd3_p_cd4_p_cd3_p_cd8_p_ratio = status.cd3_p_cd4_p_cd3_p_cd8_p_ratio,
            circulating_immune_complexes = status.circulating_immune_complexes,
            common_b_lymphocytes = status.common_b_lymphocytes,
            common_nk_cells = status.common_nk_cells,
            common_t_lymphocytes = status.common_t_lymphocytes ,
            cytokine_producing_nk_cells = status.cytokine_producing_nk_cells,
            cytolytic_nk_cells = status.cytolytic_nk_cells,
            hct_test_spontaneous = status.hct_test_spontaneous,
            hct_test_stimulated = status.hct_test_stimulated,
            leukocytes_bactericidal_activity = status.leukocytes_bactericidal_activity,
            lga = status.lga,
            lgg = status.lgg,
            lgm = status.lgm,
            monocytes_absorption_activity = status.monocytes_absorption_activity,
            neutrophil_absorption_activity = status.neutrophil_absorption_activity,
            t_cytotoxic_lymphocytes = status.t_cytotoxic_lymphocytes,
            t_helpers = status.t_helpers,
            tnk_cells = status.tnk_cells,
            id = status.id
        )
    }

    private fun toCytokineStatus(status: CytokineStatus): Cytokine {
        return Cytokine(
            cd3_m_ifny_spontaneous = status.cd3_m_ifny_spontaneous ,
            cd3_m_ifny_stimulated = status.cd3_m_ifny_stimulated,
            cd3_p_ifny_spontaneous = status.cd3_p_ifny_spontaneous,
            cd3_p_ifny_stimulated = status.cd3_p_ifny_stimulated,
            cd3_p_il2_spontaneous = status.cd3_p_il4_spontaneous,
            cd3_p_il2_stimulated = status.cd3_p_il2_stimulated,
            cd3_p_il4_spontaneous = status.cd3_p_il2_spontaneous,
            cd3_p_il4_stimulated = status.cd3_p_il4_stimulated,
            cd3_p_tnfa_spontaneous = status.cd3_p_tnfa_spontaneous,
            cd3_p_tnfa_stimulated = status.cd3_p_tnfa_stimulated,
            id = status.id,
        )
    }

    private fun toHematological(status: HematologicalStatus): Hematological {
        return Hematological(
            bas = status.bas,
            eos = status.eos,
            hct = status.hct,
            hgb = status.hgb,
            lymf = status.lymf,
            mch = status.mch,
            mpv = status.mpv,
            mvc = status.mvc,
            neu = status.neu,
            pct = status.pct,
            pdv = status.pdv,
            plt = status.plt,
            rbc = status.rbc,
            rdwcv = status.rdwcv,
            wbc = status.wbc,
            id = status.id,
        )
    }
}
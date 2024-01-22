package com.example.data.mappers

import com.example.data.api.model.analysis.AnalysisListModel
import com.example.data.api.model.analysis.AnalysisModel
import com.example.data.api.model.analysis.CytokineStatusModel
import com.example.data.api.model.analysis.HematologicalStatusModel
import com.example.data.api.model.analysis.ImmuneStatusModel
import com.example.data.api.model.analysis.PatientIdRequest
import com.example.data.api.model.analysis.StatusListModel
import com.example.domain.entity.analysis.Analysis
import com.example.domain.entity.analysis.AnalysisList
import com.example.domain.entity.analysis.CytokineStatus
import com.example.domain.entity.analysis.HematologicalStatus
import com.example.domain.entity.analysis.ImmuneStatus
import com.example.domain.entity.analysis.StatusList

class AddAnalysisApiResponseMapper {
    fun toPatientIdRequest(patientId: String) : PatientIdRequest{
        return PatientIdRequest(patientId = patientId)
    }

    fun toAnalysisModel(response : AnalysisModel) : Analysis {
        return Analysis(
            id = response.id,
            patientId = response.patientId,
            cytokineStatusId = response.cytokineStatusId,
            hematologicalStatusId = response.hematologicalStatusId,
            immuneStatusId = response.immuneStatusId,
            executionDateStr = response.executionDateStr
        )
    }

    fun toAnalysis(response : Analysis) : AnalysisModel {
        return AnalysisModel(
            id = response.id,
            patientId = response.patientId.toInt() ?: -1,
            cytokineStatusId = response.cytokineStatusId,
            hematologicalStatusId = response.hematologicalStatusId,
            immuneStatusId = response.immuneStatusId,
            executionDateStr = response.executionDateStr
        )
    }

    fun toHematologicalStatusModel(response: HematologicalStatusModel) : HematologicalStatus{
        return HematologicalStatus(
            bas = response.bas,
            eos = response.eos,
            hct = response.hct,
            hgb = response.hgb,
            lymf = response.lymf,
            mch = response.mch,
            mpv = response.mpv,
            neu = response.neu,
            pct = response.pct,
            pdv = response.pdv,
            plt = response.plt,
            rbc = response.rbc,
            rdwcv = response.rdwcv,
            wbc = response.wbc,
            mchc = response.mchc,
            mcv = response.mcv,
            mon = response.mon
        )
    }

    fun toHematologicalStatus(response: HematologicalStatus) : HematologicalStatusModel{
        return HematologicalStatusModel(
            bas = response.bas,
            eos = response.eos,
            hct = response.hct,
            hgb = response.hgb,
            lymf = response.lymf,
            mch = response.mch,
            mpv = response.mpv,
            neu = response.neu,
            pct = response.pct,
            pdv = response.pdv,
            plt = response.plt,
            rbc = response.rbc,
            rdwcv = response.rdwcv,
            wbc = response.wbc,
            mchc = response.mchc,
            mcv = response.mcv,
            mon = response.mon
        )
    }

    fun toImmuneStatus(response: ImmuneStatus) : ImmuneStatusModel{
        return ImmuneStatusModel(
            activated_t_cells = response.activated_t_cells,
            activated_t_cells_expressing_il2 = response.activated_t_cells_expressing_il2,
            cd3_p_cd4_p_cd3_p_cd8_p_ratio = response.cd3_p_cd4_p_cd3_p_cd8_p_ratio,
            circulating_immune_complexes = response.circulating_immune_complexes,
            common_b_lymphocytes = response.common_b_lymphocytes,
            common_nk_cells = response.common_nk_cells,
            common_t_lymphocytes = response.common_t_lymphocytes ,
            cytokine_producing_nk_cells = response.cytokine_producing_nk_cells,
            cytolytic_nk_cells = response.cytolytic_nk_cells,
            hct_test_spontaneous = response.hct_test_spontaneous,
            hct_test_stimulated = response.hct_test_stimulated,
            leukocytes_bactericidal_activity = response.leukocytes_bactericidal_activity,
            lga = response.lga,
            lgg = response.lgg,
            lgm = response.lgm,
            monocytes_absorption_activity = response.monocytes_absorption_activity,
            neutrophil_absorption_activity = response.neutrophil_absorption_activity,
            t_cytotoxic_lymphocytes = response.t_cytotoxic_lymphocytes,
            t_helpers = response.t_helpers,
            tnk_cells = response.tnk_cells
        )
    }

    fun toCytokineStatus(response: CytokineStatus) : CytokineStatusModel{
        return CytokineStatusModel(
            cd3_m_ifny_spontaneous = response.cd3_m_ifny_spontaneous ,
            cd3_m_ifny_stimulated = response.cd3_m_ifny_stimulated,
            cd3_p_ifny_spontaneous = response.cd3_p_ifny_spontaneous,
            cd3_p_ifny_stimulated = response.cd3_p_ifny_stimulated,
            cd3_p_il2_spontaneous = response.cd3_p_il4_spontaneous,
            cd3_p_il2_stimulated = response.cd3_p_il2_stimulated,
            cd3_p_il4_spontaneous = response.cd3_p_il2_spontaneous,
            cd3_p_il4_stimulated = response.cd3_p_il4_stimulated,
            cd3_p_tnfa_spontaneous = response.cd3_p_tnfa_spontaneous,
            cd3_p_tnfa_stimulated = response.cd3_p_tnfa_stimulated
        )
    }

    fun toAnalysisListModel(response: AnalysisListModel): List<AnalysisList> {
        var list = arrayListOf<AnalysisList>()
        for (i in response){
            val executionDateStr = i.executionDateStr
            val id = i.id
            val patientId = i.patientId
            val analysis = AnalysisList(
                id = id,
                executionDateStr = executionDateStr,
                patientId = patientId
            )
            list.add(analysis)
        }
        return list
    }

    fun toImmuneStatusModel(response : ImmuneStatusModel): ImmuneStatus {
        return ImmuneStatus(
            activated_t_cells = response.activated_t_cells,
            activated_t_cells_expressing_il2 = response.activated_t_cells_expressing_il2,
            cd3_p_cd4_p_cd3_p_cd8_p_ratio = response.cd3_p_cd4_p_cd3_p_cd8_p_ratio,
            circulating_immune_complexes = response.circulating_immune_complexes,
            common_b_lymphocytes = response.common_b_lymphocytes,
            common_nk_cells = response.common_nk_cells,
            common_t_lymphocytes = response.common_t_lymphocytes ,
            cytokine_producing_nk_cells = response.cytokine_producing_nk_cells,
            cytolytic_nk_cells = response.cytolytic_nk_cells,
            hct_test_spontaneous = response.hct_test_spontaneous,
            hct_test_stimulated = response.hct_test_stimulated,
            leukocytes_bactericidal_activity = response.leukocytes_bactericidal_activity,
            lga = response.lga,
            lgg = response.lgg,
            lgm = response.lgm,
            monocytes_absorption_activity = response.monocytes_absorption_activity,
            neutrophil_absorption_activity = response.neutrophil_absorption_activity,
            t_cytotoxic_lymphocytes = response.t_cytotoxic_lymphocytes,
            t_helpers = response.t_helpers,
            tnk_cells = response.tnk_cells
        )
    }

    fun toCytokineStatusModel(response : CytokineStatusModel): CytokineStatus {
        return CytokineStatus(
            cd3_m_ifny_spontaneous = response.cd3_m_ifny_spontaneous ,
            cd3_m_ifny_stimulated = response.cd3_m_ifny_stimulated,
            cd3_p_ifny_spontaneous = response.cd3_p_ifny_spontaneous,
            cd3_p_ifny_stimulated = response.cd3_p_ifny_stimulated,
            cd3_p_il2_spontaneous = response.cd3_p_il4_spontaneous,
            cd3_p_il2_stimulated = response.cd3_p_il2_stimulated,
            cd3_p_il4_spontaneous = response.cd3_p_il2_spontaneous,
            cd3_p_il4_stimulated = response.cd3_p_il4_stimulated,
            cd3_p_tnfa_spontaneous = response.cd3_p_tnfa_spontaneous,
            cd3_p_tnfa_stimulated = response.cd3_p_tnfa_stimulated
        )
    }

    fun toValuesStatus(body: StatusListModel): List<StatusList> {
        var list = arrayListOf<StatusList>()
        for (i in body){
            val fieldMaxValue = i.fieldMaxValue
            val fieldMinValue = i.fieldMinValue
            val fieldName = i.fieldName
            val fieldUnit = i.fieldUnit
            val fieldTitle = i.fieldTitle
            list.add(
                StatusList(
                fieldMaxValue = fieldMaxValue,
                fieldMinValue = fieldMinValue,
                fieldName = fieldName,
                fieldUnit = fieldUnit,
                fieldTitle = fieldTitle
            )
            )
        }
        return list
    }
}
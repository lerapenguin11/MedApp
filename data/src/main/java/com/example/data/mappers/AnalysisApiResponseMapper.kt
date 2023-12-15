package com.example.data.mappers

import com.example.data.api.model.analysis.AnalysisListModel
import com.example.data.api.model.analysis.AnalysisModel
import com.example.data.api.model.analysis.HematologicalStatusModel
import com.example.data.api.model.patient.PatientModel
import com.example.domain.entity.analysis.Analysis
import com.example.domain.entity.analysis.AnalysisList
import com.example.domain.entity.analysis.HematologicalStatus
import com.example.domain.entity.patient.Patients

class AnalysisApiResponseMapper {

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
            patientId = response.patientId?.toInt() ?: -1,
            cytokineStatusId = response.cytokineStatusId,
            hematologicalStatusId = response.hematologicalStatusId,
            immuneStatusId = response.immuneStatusId,
            executionDateStr = response.executionDateStr
        )
    }

    fun toHematologicalStatusModel(response: HematologicalStatusModel) : HematologicalStatus{
        return HematologicalStatus(
            wbc = response.wbc,
            lymf = response.lymf,
            neu = response.neu,
            eos = response.eos,
            bas = response.bas,
            hgb = response.hgb,
            hct = response.hct,
            plt = response.plt,
            rbc = response.rbc,
            mvc =response.mvc,
            mch = response.mch,
            rdwcv = response.rdwcv,
            mpv = response.mpv,
            pct = response.pct,
            pdv = response.pdv
        )
    }

    fun toHematologicalStatus(response: HematologicalStatus) : HematologicalStatusModel{
        return HematologicalStatusModel(
            wbc = response.wbc,
            lymf = response.lymf,
            neu = response.neu,
            eos = response.eos,
            bas = response.bas,
            hgb = response.hgb,
            hct = response.hct,
            plt = response.plt,
            rbc = response.rbc,
            mvc =response.mvc,
            mch = response.mch,
            rdwcv = response.rdwcv,
            mpv = response.mpv,
            pct = response.pct,
            pdv = response.pdv
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
}
package com.example.data.mappers

import com.example.data.api.model.analysis.AnalysisModel
import com.example.data.api.model.analysis.HematologicalStatusModel
import com.example.domain.entity.analysis.Analysis
import com.example.domain.entity.analysis.HematologicalStatus

class AnalysisApiResponseMapper {

    fun toAnalysisModel(response : AnalysisModel) : Analysis {
        return Analysis(
            id = response.id,
            patientId = response.patientId,
            cytokineStatusId = response.cytokineStatusId,
            hematologicalStatusId = response.hematologicalStatusId,
            immuneStatusId = response.immuneStatusId
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
}
package com.example.domain.repository

import com.example.domain.common.ResultMed
import com.example.domain.entity.analysis.patientAnalysis.PatientAnalysisList

interface AnalysisRepository
{
    suspend fun getPatientAnalysisList(patientId : String) : ResultMed<List<PatientAnalysisList>>
}
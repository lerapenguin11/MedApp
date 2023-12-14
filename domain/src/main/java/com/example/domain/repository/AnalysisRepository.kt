package com.example.domain.repository

import com.example.domain.common.ResultMed
import com.example.domain.entity.Analysis

interface AnalysisRepository {

    suspend fun getNewAnalysis(idPatient : String) : ResultMed<Analysis>
}
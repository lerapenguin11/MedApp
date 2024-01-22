package com.example.domain.repository

import com.example.domain.common.ResultMed


interface GraphRepository {
    suspend fun getGraph(analysisId : String): ResultMed<Any>
}
package com.example.domain.repository

import com.example.domain.common.ResultMed
import java.io.InputStream


interface GraphRepository {
    suspend fun getGraph(analysisId : String): ResultMed<InputStream>
}
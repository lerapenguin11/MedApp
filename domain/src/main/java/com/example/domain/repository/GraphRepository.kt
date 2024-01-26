package com.example.domain.repository

import com.example.domain.common.ResultMed
import java.io.InputStream


interface GraphRepository {
    suspend fun getGraphTCell(analysisId : String): ResultMed<InputStream>
    suspend fun getGraphBCell(analysisId : String): ResultMed<InputStream>
}
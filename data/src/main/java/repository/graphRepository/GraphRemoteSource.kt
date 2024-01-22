package repository.graphRepository

import android.graphics.Bitmap
import com.example.domain.common.ResultMed

interface GraphRemoteSource {
    suspend fun getGraph(analysisId : String): ResultMed<Any>
}
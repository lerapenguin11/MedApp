package repository.graphRepository

import android.graphics.Bitmap
import com.example.domain.common.ResultMed
import java.io.InputStream

interface GraphRemoteSource {
    suspend fun getGraphTCell(analysisId : String): ResultMed<InputStream>
    suspend fun getGraphBCell(analysisId : String): ResultMed<InputStream>
}
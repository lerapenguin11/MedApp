package repository.graphRepository

import android.graphics.Bitmap
import com.example.domain.common.ResultMed
import java.io.InputStream

interface GraphRemoteSource {
    suspend fun getGraph(analysisId : String): ResultMed<InputStream>
}
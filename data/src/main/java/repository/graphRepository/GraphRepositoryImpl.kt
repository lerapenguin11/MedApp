package repository.graphRepository


import android.graphics.Bitmap
import com.example.domain.common.ResultMed
import com.example.domain.repository.GraphRepository

class GraphRepositoryImpl(
    private val remoteSource : GraphRemoteSource
) : GraphRepository
{
    override suspend fun getGraph(analysisId: String): ResultMed<Any> {
        return remoteSource.getGraph(analysisId = analysisId)
    }
}
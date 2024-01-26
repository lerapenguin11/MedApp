package repository.graphRepository


import com.example.domain.common.ResultMed
import com.example.domain.repository.GraphRepository
import java.io.InputStream

class GraphRepositoryImpl(
    private val remoteSource : GraphRemoteSource
) : GraphRepository
{
    override suspend fun getGraphTCell(analysisId: String): ResultMed<InputStream> {
        return remoteSource.getGraphTCell(analysisId = analysisId)
    }

    override suspend fun getGraphBCell(analysisId: String): ResultMed<InputStream> {
        return remoteSource.getGraphBCell(analysisId = analysisId)
    }
}
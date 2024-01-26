package repository.graphRepository

import com.example.data.api.MedApi
import com.example.domain.common.ResultMed
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream

class GraphRemoteSourceImpl(
    private val service : MedApi)
    : GraphRemoteSource
{
    override suspend fun getGraphTCell(analysisId: String): ResultMed<InputStream> =
        withContext(Dispatchers.IO) {
            try {
                val response = service.getGraphTCell(analysisId = analysisId)
                if (response.isSuccessful && response.body()!=null) {
                    val responseBody = response.body()!!.byteStream()

                    return@withContext ResultMed.Success(responseBody)
                } else {
                    return@withContext ResultMed.Error(Exception(response.message()))
                }
            } catch (e: Exception) {
                return@withContext ResultMed.Error(e)
            }
        }

    override suspend fun getGraphBCell(analysisId: String): ResultMed<InputStream> =
        withContext(Dispatchers.IO) {
            try {
                val response = service.getGraphBCell(analysisId = analysisId)
                if (response.isSuccessful && response.body()!=null) {
                    val responseBody = response.body()!!.byteStream()

                    return@withContext ResultMed.Success(responseBody)
                } else {
                    return@withContext ResultMed.Error(Exception(response.message()))
                }
            } catch (e: Exception) {
                return@withContext ResultMed.Error(e)
            }
        }



}
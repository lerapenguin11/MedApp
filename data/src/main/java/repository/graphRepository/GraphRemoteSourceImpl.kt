package repository.graphRepository

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.data.api.MedApi
import com.example.domain.common.ResultMed
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream

class GraphRemoteSourceImpl(
    private val service : MedApi)
    : GraphRemoteSource
{
    override suspend fun getGraph(analysisId: String): ResultMed<Any> =
        withContext(Dispatchers.IO) {
            try {
                val response = service.getGraph(analysisId = analysisId)
                if (response.isSuccessful) {
                    val responseBody = response.body()

                    return@withContext ResultMed.Success(responseBody)
                } else {
                    return@withContext ResultMed.Error(Exception(response.message()))
                }
            } catch (e: Exception) {
                return@withContext ResultMed.Error(e)
            }
        } as ResultMed<Bitmap>



}
package com.example.medapp.viewmodel

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.common.ResultMed
import com.example.domain.usecase.graph.GetGraphUseCase
import kotlinx.coroutines.launch
import java.io.InputStream

class GraphViewModel(
    private val getGraphUseCase: GetGraphUseCase
) : ViewModel()
{
    private val _graph = MutableLiveData<Bitmap?>()
    val graph: LiveData<Bitmap?> get() = _graph

    private val _anyGraph = MutableLiveData<Any?>()
    fun getGraph(analysisId : String) {
        viewModelScope.launch {
            when (val graphResult =
                getGraphUseCase.invoke(analysisId = analysisId)) {
                is ResultMed.Success -> {
                    val inputStream = graphResult.data
                    val bitmap = inputStream.let { it1 -> convertInputStreamToBitmap(it1) }
                    _graph.value = bitmap
                }

                is ResultMed.Error -> {

                }
            }
        }
    }

    private fun convertInputStreamToBitmap(inputStream: InputStream): Bitmap {
        return BitmapFactory.decodeStream(inputStream)
    }
}
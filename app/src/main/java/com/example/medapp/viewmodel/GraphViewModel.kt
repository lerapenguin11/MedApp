package com.example.medapp.viewmodel

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.common.ResultMed
import com.example.domain.usecase.graph.GetGraphBCellUseCase
import com.example.domain.usecase.graph.GetGraphTCellUseCase
import kotlinx.coroutines.launch
import java.io.InputStream

class GraphViewModel(
    private val getGraphTCellUseCase: GetGraphTCellUseCase,
    private val getGraphBCellUseCase: GetGraphBCellUseCase
) : ViewModel()
{
    private val _graphT = MutableLiveData<Bitmap?>()
    val graphT: LiveData<Bitmap?> get() = _graphT

    private val _graphB = MutableLiveData<Bitmap?>()
    val graphB: LiveData<Bitmap?> get() = _graphB

    private val _anyGraph = MutableLiveData<Any?>()
    fun getGraphTCell(analysisId : String) {
        viewModelScope.launch {
            when (val graphResult =
                getGraphTCellUseCase.invoke(analysisId = analysisId)) {
                is ResultMed.Success -> {
                    val inputStream = graphResult.data
                    val bitmap = inputStream.let { it1 -> convertInputStreamToBitmap(it1) }
                    _graphT.value = bitmap
                }

                is ResultMed.Error -> {

                }
            }
        }
    }

    fun getGraphBCell(analysisId : String) {
        viewModelScope.launch {
            when (val graphResult =
                getGraphBCellUseCase.invoke(analysisId = analysisId)) {
                is ResultMed.Success -> {
                    val inputStream = graphResult.data
                    val bitmap = inputStream.let { it1 -> convertInputStreamToBitmap(it1) }
                    _graphB.value = bitmap
                }

                is ResultMed.Error -> {}
            }
        }
    }

    private fun convertInputStreamToBitmap(inputStream: InputStream): Bitmap {
        return BitmapFactory.decodeStream(inputStream)
    }
}
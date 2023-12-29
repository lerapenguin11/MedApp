package com.example.medapp.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.domain.entity.analysis.patientAnalysis.PatientAnalysisList
import com.example.medapp.R
import com.example.medapp.presentation.adapter.diffCallback.AnalysisItemDiffCallback
import com.example.medapp.presentation.adapter.viewholder.AnalysisViewHolder

class AnalysisAdapter : ListAdapter<PatientAnalysisList, AnalysisViewHolder>(
    AnalysisItemDiffCallback()) {

    var onAnalysisClickListener : ((PatientAnalysisList) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnalysisViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_analyzes, parent, false)
        return AnalysisViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: AnalysisViewHolder, position: Int) {
        val analysis = getItem(position)
        holder.dateAnalysis.text = analysis.executionDateStr
        holder.numberAnalysis.text = "$TEXT_ANALYSIS${analysis.id}"
        holder.itemView.setOnClickListener {
            onAnalysisClickListener?.invoke(analysis)
        }
    }

    companion object{
        private const val TEXT_ANALYSIS = "Анализ №"
    }
}
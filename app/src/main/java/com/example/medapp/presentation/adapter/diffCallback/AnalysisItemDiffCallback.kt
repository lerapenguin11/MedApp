package com.example.medapp.presentation.adapter.diffCallback

import androidx.recyclerview.widget.DiffUtil
import com.example.domain.entity.analysis.patientAnalysis.PatientAnalysisList

class AnalysisItemDiffCallback : DiffUtil.ItemCallback<PatientAnalysisList>() {
    override fun areItemsTheSame(
        oldItem: PatientAnalysisList,
        newItem: PatientAnalysisList
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: PatientAnalysisList,
        newItem: PatientAnalysisList
    ): Boolean {
        return oldItem == newItem
    }
}
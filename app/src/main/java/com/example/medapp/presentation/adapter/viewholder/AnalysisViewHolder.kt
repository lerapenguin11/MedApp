package com.example.medapp.presentation.adapter.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.medapp.R

class AnalysisViewHolder(view: View) : RecyclerView.ViewHolder(view)
{
    val numberAnalysis : TextView = view.findViewById(R.id.tv_number_analyzes)
    val dateAnalysis : TextView = view.findViewById(R.id.tv_date_analyzes)
}
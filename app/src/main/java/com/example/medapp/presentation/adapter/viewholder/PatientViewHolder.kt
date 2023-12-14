package com.example.medapp.presentation.adapter.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.medapp.R

class PatientViewHolder(view : View) : RecyclerView.ViewHolder(view){
    val name : TextView = view.findViewById(R.id.tv_name_pasient)
    val diagnosis : TextView = view.findViewById(R.id.tv_diagnosis)
    val dateBirth : TextView = view.findViewById(R.id.tv_date_birth)
    //val dateAnalysis : TextView = view.findViewById(R.id.tv_date_analysis)
}
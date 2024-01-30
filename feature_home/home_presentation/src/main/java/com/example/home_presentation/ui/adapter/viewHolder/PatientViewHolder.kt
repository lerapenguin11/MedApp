package com.example.home_presentation.ui.adapter.viewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.home_presentation.R

class PatientViewHolder(view : View) : RecyclerView.ViewHolder(view)
{
    val name : TextView = view.findViewById(R.id.tv_name_pasient)
    val diagnosis : TextView = view.findViewById(R.id.tv_diagnosis)
    val dateBirth : TextView = view.findViewById(R.id.tv_date_birth)
}
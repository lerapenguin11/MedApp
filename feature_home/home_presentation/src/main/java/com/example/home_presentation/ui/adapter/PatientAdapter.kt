package com.example.home_presentation.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.home_domain.entity.Patients
import com.example.home_presentation.R
import com.example.home_presentation.ui.adapter.itemDiffCallback.PatientItemDiffCallback
import com.example.home_presentation.ui.adapter.viewHolder.PatientViewHolder

class PatientAdapter : ListAdapter<Patients, PatientViewHolder>(
    PatientItemDiffCallback()
)
{
    var onPatientClickListener : ((Patients) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card_patient, parent, false)

        return PatientViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PatientViewHolder, position: Int) {
        val patient = getItem(position)
        holder.name.setText("${patient.lastName} ${patient.firstName} ${patient.middleName}")
        holder.diagnosis.text = patient?.diagnosis ?: CONST_STRING_NO
        holder.dateBirth.text = patient?.birthDateStr ?: CONST_STRING_NO
        holder.itemView.setOnClickListener {
            onPatientClickListener?.invoke(patient)
        }
    }



    companion object{
        const val CONST_STRING_NO = "нет"
    }
}
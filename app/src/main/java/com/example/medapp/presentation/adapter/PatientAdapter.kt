package com.example.medapp.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.domain.entity.patient.Patients
import com.example.medapp.presentation.adapter.diffCallback.PatientItemDiffCallback
import com.example.medapp.presentation.adapter.viewholder.PatientViewHolder
import androidx.recyclerview.widget.ListAdapter
import com.example.medapp.R

class PatientAdapter : ListAdapter<Patients, PatientViewHolder>(
    PatientItemDiffCallback())
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
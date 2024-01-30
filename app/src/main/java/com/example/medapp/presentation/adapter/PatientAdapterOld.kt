package com.example.medapp.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.domain.entity.patient.Patients
import com.example.medapp.presentation.adapter.diffCallback.PatientItemDiffCallbackOld
import com.example.medapp.presentation.adapter.viewholder.PatientViewHolderOld
import androidx.recyclerview.widget.ListAdapter
import com.example.medapp.R

class PatientAdapterOld : ListAdapter<Patients, PatientViewHolderOld>(
    PatientItemDiffCallbackOld())
{
    var onPatientClickListener : ((Patients) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientViewHolderOld {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card_patient, parent, false)

        return PatientViewHolderOld(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PatientViewHolderOld, position: Int) {
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
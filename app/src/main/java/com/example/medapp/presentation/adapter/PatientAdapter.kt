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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card_patient, parent, false)

        return PatientViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PatientViewHolder, position: Int) {
        val payment = getItem(position)
        holder.name.setText("${payment.lastName} ${payment.firstName} ${payment.middleName}")
        holder.diagnosis.text = payment.diagnosis.toString()
        holder.dateBirth.text = payment.age.toString()
    }
}
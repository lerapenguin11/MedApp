package com.example.medapp.presentation.adapter.diffCallback

import androidx.recyclerview.widget.DiffUtil
import com.example.domain.entity.Patients

class PatientItemDiffCallback : DiffUtil.ItemCallback<Patients>() {
    override fun areItemsTheSame(oldItem: Patients, newItem: Patients): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Patients, newItem: Patients): Boolean {
        return oldItem == newItem
    }
}
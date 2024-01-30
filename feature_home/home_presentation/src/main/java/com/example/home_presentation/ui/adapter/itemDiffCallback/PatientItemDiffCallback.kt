package com.example.home_presentation.ui.adapter.itemDiffCallback

import androidx.recyclerview.widget.DiffUtil
import com.example.home_domain.entity.Patients

class PatientItemDiffCallback : DiffUtil.ItemCallback<Patients>()
{
    override fun areItemsTheSame(oldItem: Patients, newItem: Patients): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Patients, newItem: Patients): Boolean {
        return oldItem == newItem
    }
}
package com.example.medapp.presentation.adapter.diffCallback

import androidx.recyclerview.widget.DiffUtil
import com.example.domain.entity.analysis.StatusList

class AddStatusImmuneItemDiffCallback : DiffUtil.ItemCallback<StatusList>() {
    override fun areItemsTheSame(oldItem: StatusList, newItem: StatusList): Boolean {
        return oldItem.fieldName == newItem.fieldName
    }

    override fun areContentsTheSame(oldItem: StatusList, newItem: StatusList): Boolean {
        return oldItem == newItem
    }
}
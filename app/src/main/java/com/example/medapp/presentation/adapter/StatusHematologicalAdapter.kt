package com.example.medapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.domain.entity.analysis.StatusList
import com.example.medapp.R
import com.example.medapp.presentation.adapter.diffCallback.AddStatusHematologicalItemDiffCallback
import com.example.medapp.presentation.adapter.viewholder.AddStatusHematologicalViewHolder

class StatusHematologicalAdapter : ListAdapter<StatusList, AddStatusHematologicalViewHolder>(
    AddStatusHematologicalItemDiffCallback()
)
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddStatusHematologicalViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_input_analysis, parent, false)
        return AddStatusHematologicalViewHolder(view)
    }

    override fun onBindViewHolder(holder: AddStatusHematologicalViewHolder, position: Int) {
        val status = getItem(position)
        holder.indicator.text = status.fieldTitle
        holder.meaning_text.hint = "${status.fieldMinValue}${DASH}${status.fieldMaxValue}"
    }

    companion object{
        private const val DASH = "-"
    }
}
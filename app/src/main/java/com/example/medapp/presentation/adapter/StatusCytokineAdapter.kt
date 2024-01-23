package com.example.medapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.domain.entity.analysis.StatusList
import com.example.medapp.R
import com.example.medapp.presentation.adapter.diffCallback.AddStatusCytokineItemDiffCallback
import com.example.medapp.presentation.adapter.viewholder.AddStatusCytokineViewHolder

class StatusCytokineAdapter : ListAdapter<StatusList, AddStatusCytokineViewHolder>(
    AddStatusCytokineItemDiffCallback())
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddStatusCytokineViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_input_analysis, parent, false)
        return AddStatusCytokineViewHolder(view)
    }

    override fun onBindViewHolder(holder: AddStatusCytokineViewHolder, position: Int) {
        val status = getItem(position)
        holder.indicator.text = status.fieldTitle
        holder.meaning_text.hint = "${status.fieldMinValue}${DASH}${status.fieldMaxValue}"
    }

    companion object{
        private const val DASH = "-"
    }
}
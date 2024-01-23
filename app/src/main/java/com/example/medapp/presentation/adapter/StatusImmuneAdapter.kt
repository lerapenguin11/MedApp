package com.example.medapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.domain.entity.analysis.StatusList
import com.example.medapp.R
import com.example.medapp.presentation.adapter.diffCallback.AddStatusImmuneItemDiffCallback
import com.example.medapp.presentation.adapter.viewholder.AddStatusImmuneViewHolder

class StatusImmuneAdapter : ListAdapter<StatusList, AddStatusImmuneViewHolder>(
    AddStatusImmuneItemDiffCallback()
)
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddStatusImmuneViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_input_analysis, parent, false)
        return AddStatusImmuneViewHolder(view)
    }

    override fun onBindViewHolder(holder: AddStatusImmuneViewHolder, position: Int) {
        val status = getItem(position)
        holder.indicator.text = status.fieldTitle
        holder.meaning_text.hint = "${status.fieldMinValue}${DASH}${status.fieldMaxValue}"
    }

    companion object{
        private const val DASH = "-"
    }
}
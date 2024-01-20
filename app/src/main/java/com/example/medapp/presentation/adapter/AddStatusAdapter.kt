package com.example.medapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.domain.entity.analysis.StatusList
import com.example.medapp.R
import com.example.medapp.presentation.adapter.diffCallback.AddStatusItemDiffCallback
import com.example.medapp.presentation.adapter.viewholder.AddStatusViewHolder

class AddStatusAdapter : ListAdapter<StatusList, AddStatusViewHolder>(
    AddStatusItemDiffCallback()
)
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddStatusViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_input_analysis, parent, false)
        return AddStatusViewHolder(view)
    }

    override fun onBindViewHolder(holder: AddStatusViewHolder, position: Int) {
        val status = getItem(position)
        holder.indicator.text = status.fieldTitle
        holder.meaning.hint = status.fieldMinValue.toString()/*"${status.fieldMinValue}${DASH}${status.fieldMaxValue}"*/
        holder.meaning_text.hint = "${status.fieldMinValue}${DASH}${status.fieldMaxValue}"
    }

    companion object{
        private const val DASH = "-"
    }
}
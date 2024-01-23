package com.example.medapp.presentation.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.domain.entity.analysis.HematologicalStatus
import com.example.domain.entity.analysis.StatusList
import com.example.medapp.R
import com.example.medapp.presentation.adapter.diffCallback.AddStatusHematologicalItemDiffCallback
import com.example.medapp.presentation.adapter.viewholder.AddStatusHematologicalViewHolder
import com.google.android.material.textfield.TextInputEditText

class StatusHematologicalAdapter : ListAdapter<StatusList, AddStatusHematologicalViewHolder>(
    AddStatusHematologicalItemDiffCallback()
)
{
    var onMeaningClickListener : ((HematologicalStatus) -> Unit)? = null
    private var list = arrayListOf<String>()

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
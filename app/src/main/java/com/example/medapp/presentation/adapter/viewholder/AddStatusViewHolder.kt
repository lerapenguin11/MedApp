package com.example.medapp.presentation.adapter.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.medapp.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class AddStatusViewHolder(view : View) : RecyclerView.ViewHolder(view)
{
    val indicator : TextView = view.findViewById(R.id.tv_indicator)
    val meaning : TextInputEditText = view.findViewById(R.id.et_meaning)
    val meaning_text : TextInputLayout = view.findViewById(R.id.text_input_meaning)
}
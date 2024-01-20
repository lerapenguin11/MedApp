package com.example.data.api.model.analysis

class StatusListModel : ArrayList<StatusListModelItem>()

data class StatusListModelItem(
    val fieldMaxValue: Double,
    val fieldMinValue: Double,
    val fieldName: String,
    val fieldUnit: String,
    val fieldTitle : String
)

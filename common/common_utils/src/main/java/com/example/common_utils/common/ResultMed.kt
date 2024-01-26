package com.example.common_utils.common

sealed class ResultMed<out R> {

    data class Success<out T>(val data: T) : ResultMed<T>()
    data class Error(val exception: Exception) : ResultMed<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}
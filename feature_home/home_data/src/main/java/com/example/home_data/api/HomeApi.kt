package com.example.home_data.api

import com.example.home_data.models.PatientModel
import retrofit2.Response
import retrofit2.http.GET

interface HomeApi
{
    @GET("patient")
    suspend fun getAllPatient(): Response<PatientModel> // Получение списка всех пациентов
}
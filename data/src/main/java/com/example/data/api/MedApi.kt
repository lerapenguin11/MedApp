package com.example.data.api

import com.example.data.api.model.patient.NewPatientIdModel
import com.example.data.api.model.patient.PatientAdd
import com.example.data.api.model.patient.PatientModel
import retrofit2.Response
import retrofit2.http.*

interface MedApi {

    @GET("patients")
    suspend fun getPatient(): Response<PatientModel>

    @POST("patient")
    suspend fun getAddPatient(@Body requestBody: PatientAdd) : Response<NewPatientIdModel>

    @GET("/patient/{patientId}")
    suspend fun getNewPatientId(@Path("patientId") patientId: String): Response<NewPatientIdModel>
}
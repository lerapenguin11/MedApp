package com.example.data.api

import com.example.data.api.model.analysis.AnalysisListModel
import com.example.data.api.model.analysis.AnalysisModel
import com.example.data.api.model.analysis.HematologicalStatusModel
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

    @GET("patient/{patientId}")
    suspend fun getNewPatientId(@Path("patientId") patientId: String): Response<NewPatientIdModel>

    @POST("patient/{patientId}/analysis")
    suspend fun getNewAnalysis(@Path("patientId") patientId: String) : Response<AnalysisModel>

    @POST("patient/{patientId}/analysis/{analysisId}/status/hematological")
    suspend fun getAddHematologicalStatus(
        @Path("patientId") patientId: String,
        @Path("analysisId") analysisId : String,
        @Body status : HematologicalStatusModel) : Response<HematologicalStatusModel>

    @PUT("patient/{patientId}/analysis/{analysisId}")
    suspend fun getUpdateAnalysisDate(
        @Body date : AnalysisModel,
        @Path("patientId") patientId: String,
        @Path("analysisId") analysisId : String
    ) : Response<AnalysisModel>

    @GET("patient/{patientId}/analyzes")
    suspend fun getAnalysisList(
        @Path("patientId") patientId: String
    ) : Response<AnalysisListModel>
}
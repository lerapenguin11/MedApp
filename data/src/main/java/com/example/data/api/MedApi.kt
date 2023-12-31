package com.example.data.api

import com.example.data.api.model.analysis.AnalysisListModel
import com.example.data.api.model.analysis.AnalysisModel
import com.example.data.api.model.analysis.CytokineStatusModel
import com.example.data.api.model.analysis.HematologicalStatusModel
import com.example.data.api.model.analysis.ImmuneStatusModel
import com.example.data.api.model.analysis.PatientAnalysisListModel
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

    @GET("patient/{id}")
    suspend fun getNewPatientId(@Path("id") patientId: String): Response<NewPatientIdModel>

    @POST("patient/{patientId}/analysis")
    suspend fun getNewAnalysis(@Path("patientId") patientId: String) : Response<AnalysisModel>

    @POST("patient/{patientId}/analysis/{analysisId}/status/hematological")
    suspend fun getAddHematologicalStatus(
        @Path("patientId") patientId: String,
        @Path("analysisId") analysisId : String,
        @Body status : HematologicalStatusModel) : Response<HematologicalStatusModel>

    @POST("patient/{patientId}/analysis/{analysisId}/status/immune")
    suspend fun getAddImmuneStatus(
        @Path("patientId") patientId: String,
        @Path("analysisId") analysisId : String,
        @Body status : ImmuneStatusModel) : Response<ImmuneStatusModel>

    @POST("patient/{patientId}/analysis/{analysisId}/status/cytokine")
    suspend fun getAddCytokinStatus(
        @Path("patientId") patientId: String,
        @Path("analysisId") analysisId : String,
        @Body status : CytokineStatusModel) : Response<CytokineStatusModel>


    @PUT("patient/{patientId}/analysis/{analysisId}")
    suspend fun getUpdateAnalysisDate(
        @Body date : AnalysisModel,
        @Path("patientId") patientId: String,
        @Path("analysisId") analysisId : String
    ) : Response<AnalysisModel>

    @GET("patient/{id}/analyzes")
    suspend fun getAnalysisList(
        @Path("id") patientId: String
    ) : Response<AnalysisListModel>

    @GET("patient/{id}/analyzes")
    suspend fun getPatientAnalysisList(
        @Path("id") patientId: String
    ) : Response<PatientAnalysisListModel>
}
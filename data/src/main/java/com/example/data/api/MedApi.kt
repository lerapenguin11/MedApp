package com.example.data.api

import com.example.data.api.model.analysis.AnalysisListModel
import com.example.data.api.model.analysis.AnalysisModel
import com.example.data.api.model.analysis.CytokineStatusModel
import com.example.data.api.model.analysis.HematologicalStatusModel
import com.example.data.api.model.analysis.ImmuneStatusModel
import com.example.data.api.model.analysis.PatientAnalysisListModel
import com.example.data.api.model.analysis.PatientIdRequest
import com.example.data.api.model.patient.NewPatientIdModel
import com.example.data.api.model.patient.PatientAdd
import com.example.data.api.model.patient.PatientModel
import retrofit2.Response
import retrofit2.http.*

interface MedApi {
    /*
    Patient routes:
    GET /patient/:id - получение пациента по id - да
    GET /patient - получение всех пациентов - да
    POST /patient - создание пациента - да
    PUT /patient/:id - обновление пациента
    DELETE /patient/:id - удаление пациента
    GET /patient/:id/analysis - получение анализов пациента с id=id

    Analysis routes
    GET /analysis/:id - получение анализа (включая статусы по id)
    GET /analysis - получение всех анализов - да patient/{id}/analysis
    POST /analysis - создание анализа - ?
    PUT /analysis/:id - обновление анализа
    DELETE /analysis/:id - удаление анализа
    POST /analysis/:id/status/:type - создание (добавление) статуса к анализу (id - id анализа, type - тип статуса (значения: hematological, cytokine, immune)) - ?
    PUT /analysis/:id/status/:type - обновление статуса анализа (id - id анализа, type - тип статуса (значения: hematological, cytokine, immune))
    GET /analysis/template/:type - получение нормальных значений для статуса (type - тип статуса (значения: hematological, cytokine, immune))
    PUT /analysis/template/:type - изменение нормальных значений для статуса (type - тип статуса (значения: hematological, cytokine, immune))
*/

    @GET("patient")
    suspend fun getAllPatient(): Response<PatientModel> //получение всех пациентов

    @POST("patient")
    suspend fun getAddPatient(@Body requestBody: PatientAdd) : Response<NewPatientIdModel> // создание пациента

    @GET("patient/{id}")
    suspend fun getPatientId(@Path("id") patientId: String): Response<NewPatientIdModel> // получение пациента

    @POST("analysis") //TODO: rename to createAnalysis
    suspend fun getNewAnalysis(@Body patientId : PatientIdRequest) : Response<AnalysisModel> //TODO создание анализа

    //POST /analysis/:id/status/:type
    @POST("analysis/{id}/status/hematological")
    suspend fun getAddHematologicalStatus(
        //@Path("patientId") patientId: String,
        @Path("id") analysisId : String,
        @Body status : HematologicalStatusModel) : Response<HematologicalStatusModel> //TODO ?

    @POST("patient/{patientId}/analysis/{analysisId}/status/immune")
    suspend fun getAddImmuneStatus(
        @Path("patientId") patientId: String,
        @Path("analysisId") analysisId : String,
        @Body status : ImmuneStatusModel) : Response<ImmuneStatusModel> //TODO

    @POST("patient/{patientId}/analysis/{analysisId}/status/cytokine")
    suspend fun getAddCytokinStatus(
        @Path("patientId") patientId: String,
        @Path("analysisId") analysisId : String,
        @Body status : CytokineStatusModel) : Response<CytokineStatusModel> //TODO


    @PUT("patient/{patientId}/analysis/{analysisId}")
    suspend fun getUpdateAnalysisDate(
        @Body date : AnalysisModel,
        @Path("patientId") patientId: String,
        @Path("analysisId") analysisId : String
    ) : Response<AnalysisModel> //TODO

    @GET("patient/{id}/analysis")
    suspend fun getAnalysisList(
        @Path("id") patientId: String
    ) : Response<AnalysisListModel> //TODO

    @GET("patient/{id}/analysis")
    suspend fun getPatientAnalysisList(
        @Path("id") patientId: String
    ) : Response<PatientAnalysisListModel> // получение всех анализов
}
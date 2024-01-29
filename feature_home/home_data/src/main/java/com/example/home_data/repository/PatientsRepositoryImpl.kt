package com.example.home_data.repository

import com.example.common_utils.common.ResultMed
import com.example.home_data.api.HomeApi
import com.example.home_data.mappers.PatientApiResponseMapper
import com.example.home_domain.entity.Patients
import com.example.home_domain.repository.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PatientsRepositoryImpl(
    private val service : HomeApi,
    private val mapper : PatientApiResponseMapper
) : HomeRepository{
    override suspend fun getPatientList(): ResultMed<List<Patients>> =
        withContext(Dispatchers.IO) {
            try {
                val response = service.getAllPatient()
                if (response.isSuccessful) {

                    return@withContext ResultMed.Success(mapper.toPatientList(response.body()!!))
                } else {
                    return@withContext ResultMed.Error(Exception(response.message()))
                }
            } catch (e: Exception) {
                return@withContext ResultMed.Error(e)
            }
        }
}
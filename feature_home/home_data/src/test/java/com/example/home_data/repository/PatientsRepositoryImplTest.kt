package com.example.home_data.repository

import com.example.common_utils.common.ResultMed
import com.example.home_data.api.HomeApi
import com.example.home_data.mappers.PatientApiResponseMapper
import com.example.home_data.models.PatientModel
import com.example.home_domain.entity.Patients
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.mockito.kotlin.anyOrNull
import org.mockito.kotlin.mock
import retrofit2.Response

class PatientsRepositoryImplTest {

    private val mockService = mock<HomeApi>()
    private val mockMapper = PatientApiResponseMapper()
    private val patientsRepository = PatientsRepositoryImpl(mockService, mockMapper)

    @Test
    fun getPatientList_success() {
        runBlocking {
            val mockResponse = mock<Response<PatientModel>>()
            val mockPatientModel = mock(PatientModel::class.java)
            `when`(mockService.getAllPatient()).thenReturn(mockResponse)
            `when`(mockResponse.isSuccessful).thenReturn(true)
            `when`(mockResponse.body()).thenReturn(mockPatientModel)
            `when`(mockMapper.toPatientList(mockPatientModel)).thenReturn(
                listOf(
                    Patients(
                        "12.04.2000",
                        "12-01-2000", 1,
                        "Рак", "123456",
                        "1234", "Jane", 1, "Smith",
                        "dfjjdf", "2023-12-15T05:25:15.093Z"
                    )
                )
            )
            val result = patientsRepository.getPatientList()
            assertTrue(result is ResultMed.Success)
            assertNotNull((result as ResultMed.Success).data)
        }
    }

    @Test
    fun getPatientList_failure() {
        runBlocking {
            val mockResponse = mock(Response::class.java) as Response<PatientModel>
            `when`(mockService.getAllPatient()).thenReturn(mockResponse)
            `when`(mockResponse.isSuccessful).thenReturn(false)
            `when`(mockResponse.message()).thenReturn("Error message")
            val result = patientsRepository.getPatientList()
            assertTrue(result is ResultMed.Error)
            assertNotNull((result as ResultMed.Error).exception)
        }
    }

    @Test
    fun getPatientList_exception() {
        runBlocking {
            val result = patientsRepository.getPatientList()
            assertTrue(result is ResultMed.Error)
            assertNotNull((result as ResultMed.Error).exception)
        }
    }
}
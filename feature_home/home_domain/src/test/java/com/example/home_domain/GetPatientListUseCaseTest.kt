package com.example.home_domain

import com.example.common_utils.common.ResultMed
import com.example.home_domain.entity.Patients
import com.example.home_domain.repository.HomeRepository
import com.example.home_domain.usecase.GetPatientListUseCase
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class GetPatientListUseCaseTest {

    @Mock
    private lateinit var repository: HomeRepository

    private lateinit var useCase: GetPatientListUseCase

    @BeforeEach
    fun setup() {
        MockitoAnnotations.initMocks(this)
        useCase = GetPatientListUseCase(repository)
    }

    @Test
    suspend fun `invoke should return patient list from repository`() {
        val expectedPatientList = listOf(
            Patients("12.04.2000",
                "12-01-2000", null,
                "Рак", "123456",
                "1234", "Jane", 1, "Smith",
                "dfjjdf", "2023-12-15T05:25:15.093Z"),
            Patients("12.04.2000",
                "12-01-2000", null,
                "Рак", "123456",
                "1234", "Jane", 1, "Smith",
                "dfjjdf", "2023-12-15T05:25:15.093Z")
        )
        val expectedResult = ResultMed.Success(expectedPatientList)
        `when`(repository.getPatientList()).thenReturn(expectedResult)

        val result = runBlocking { useCase.invoke() }
        assertEquals(expectedResult, result)
    }
}
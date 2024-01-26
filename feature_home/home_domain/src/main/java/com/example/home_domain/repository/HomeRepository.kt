package com.example.home_domain.repository

import com.example.common_utils.common.ResultMed
import com.example.home_domain.entity.Patients

interface HomeRepository
{
    suspend fun getPatientList(): ResultMed<List<Patients>>
}
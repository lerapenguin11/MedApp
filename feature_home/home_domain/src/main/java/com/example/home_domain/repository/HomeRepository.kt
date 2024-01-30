package com.example.home_domain.repository

import com.example.common_utils.common.ResultMed
import com.example.home_domain.di.HomeDomainModule
import com.example.home_domain.entity.Patients
import dagger.Provides

interface HomeRepository
{
    suspend fun getPatientList(): ResultMed<List<Patients>>
}
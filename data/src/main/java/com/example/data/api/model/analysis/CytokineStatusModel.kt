package com.example.data.api.model.analysis

data class CytokineStatusModel(
    val cd3_m_ifny_spontaneous: Double,
    val cd3_m_ifny_stimulated: Double,
    val cd3_p_ifny_spontaneous: Double,
    val cd3_p_ifny_stimulated: Double,
    val cd3_p_il2_spontaneous: Double,
    val cd3_p_il2_stimulated: Double,
    val cd3_p_il4_spontaneous: Double,
    val cd3_p_il4_stimulated: Double,
    val cd3_p_tnfa_spontaneous: Double,
    val cd3_p_tnfa_stimulated: Double
)
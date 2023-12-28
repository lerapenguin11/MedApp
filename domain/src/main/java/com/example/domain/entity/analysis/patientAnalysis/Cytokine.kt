package com.example.domain.entity.analysis.patientAnalysis

data class Cytokine(
    val cd3_m_ifny_spontaneous: Double,
    val cd3_m_ifny_stimulated: Double,
    val cd3_p_ifny_spontaneous: Double,
    val cd3_p_ifny_stimulated: Double,
    val cd3_p_il2_spontaneous: Double,
    val cd3_p_il2_stimulated: Double,
    val cd3_p_il4_spontaneous: Double,
    val cd3_p_il4_stimulated: Double,
    val cd3_p_tnfa_spontaneous: Double,
    val cd3_p_tnfa_stimulated: Double,
    val id: Int,
)

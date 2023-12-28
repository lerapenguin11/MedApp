package com.example.domain.entity.analysis

data class ImmuneStatus(
    val activated_t_cells: Double,
    val activated_t_cells_expressing_il2: Double,
    val cd3_p_cd4_p_cd3_p_cd8_p_ratio: Double,
    val circulating_immune_complexes: Double,
    val common_b_lymphocytes: Double,
    val common_nk_cells: Double,
    val common_t_lymphocytes: Double,
    val cytokine_producing_nk_cells: Double,
    val cytolytic_nk_cells: Double,
    val hct_test_spontaneous: Double,
    val hct_test_stimulated: Double,
    val leukocytes_bactericidal_activity: Double,
    val lga: Double,
    val lgg: Double,
    val lgm: Double,
    val monocytes_absorption_activity: Double,
    val neutrophil_absorption_activity: Double,
    val t_cytotoxic_lymphocytes: Double,
    val t_helpers: Double,
    val tnk_cells: Double
)

package com.superherosquadmaker.entity

import org.threeten.bp.LocalDate

data class Issue(
    val id: Int,
    val title: String,
    val lastUpdatedDate: LocalDate,
    val weekNumberInYear: Int
)
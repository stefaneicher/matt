package com.stefaneicher.matt.domain.model

data class PointAccount(
    val id: String,
    val childId: String,
    val familyId: String,
    val balance: Int,
    val totalEarned: Int,
    val totalSpent: Int
)

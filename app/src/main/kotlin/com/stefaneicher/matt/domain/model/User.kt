package com.stefaneicher.matt.domain.model

data class User(
    val id: String,
    val name: String,
    val email: String,
    val role: UserRole,
    val familyId: String?,
    val avatarUrl: String? = null,
    val characterId: String? = null
)

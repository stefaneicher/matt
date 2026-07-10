package com.stefaneicher.matt.domain.model

data class Character(
    val id: String,
    val name: String,
    val description: String,
    val avatarResKey: String, // resource key for local asset or URL
    val unlockedAtPoints: Int = 0 // unlock threshold
)

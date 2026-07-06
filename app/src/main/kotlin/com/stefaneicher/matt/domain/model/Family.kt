package com.stefaneicher.matt.domain.model

data class Family(
    val id: String,
    val name: String,
    val parentIds: List<String>,
    val childIds: List<String>
)

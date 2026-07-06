package com.stefaneicher.matt.domain.model

import java.time.Instant

data class Action(
    val id: String,
    val familyId: String,
    val createdByParentId: String,
    val title: String,
    val description: String,
    val pointCost: Int, // positive value; deducted from balance
    val createdAt: Instant = Instant.now()
) {
    init {
        require(pointCost > 0) { "Action point cost must be positive" }
    }
}

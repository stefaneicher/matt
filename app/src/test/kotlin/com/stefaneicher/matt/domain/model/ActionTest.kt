package com.stefaneicher.matt.domain.model

import org.junit.Assert.*
import org.junit.Test

class ActionTest {

    @Test
    fun `action with positive cost is valid`() {
        val action = Action("1", "f1", "p1", "Ice cream", "", 10)
        assertEquals(10, action.pointCost)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `action with zero cost throws exception`() {
        Action("1", "f1", "p1", "Free", "", 0)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `action with negative cost throws exception`() {
        Action("1", "f1", "p1", "Bonus", "", -5)
    }
}

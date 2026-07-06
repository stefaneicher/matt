package com.stefaneicher.matt.domain.model

enum class EventType {
    TASK_COMPLETED,     // points earned — triggers reward animation
    ACTION_REDEEMED,    // points spent — triggers deduction animation
    BONUS_AWARDED,      // extra bonus from parent — triggers bonus animation
    MILESTONE_REACHED,  // balance milestone — triggers milestone animation
    TASK_CREATED,
    ACTION_CREATED
}

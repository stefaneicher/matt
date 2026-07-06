package com.stefaneicher.matt.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.stefaneicher.matt.domain.model.PointAccount

@Entity(tableName = "point_accounts")
data class PointAccountEntity(
    @PrimaryKey val id: String,
    val childId: String,
    val familyId: String,
    val balance: Int,
    val totalEarned: Int,
    val totalSpent: Int
) {
    fun toDomain() = PointAccount(
        id = id,
        childId = childId,
        familyId = familyId,
        balance = balance,
        totalEarned = totalEarned,
        totalSpent = totalSpent
    )

    companion object {
        fun fromDomain(account: PointAccount) = PointAccountEntity(
            id = account.id,
            childId = account.childId,
            familyId = account.familyId,
            balance = account.balance,
            totalEarned = account.totalEarned,
            totalSpent = account.totalSpent
        )
    }
}

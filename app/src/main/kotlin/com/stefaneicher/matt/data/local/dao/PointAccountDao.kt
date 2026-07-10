package com.stefaneicher.matt.data.local.dao

import androidx.room.*
import com.stefaneicher.matt.data.local.entity.PointAccountEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PointAccountDao {
    @Query("SELECT * FROM point_accounts WHERE childId = :childId")
    fun getPointAccount(childId: String): Flow<PointAccountEntity?>

    @Query("SELECT * FROM point_accounts WHERE childId = :childId")
    suspend fun getPointAccountOnce(childId: String): PointAccountEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(account: PointAccountEntity)
}

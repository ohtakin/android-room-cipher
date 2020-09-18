package com.tioh.roomcipher

import androidx.room.*

@Dao
interface UserDao {
    @Query("SELECT * FROM tb_user")
    fun getAll(): List<User>

    @Query("SELECT * FROM tb_user WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Query("SELECT * FROM tb_user WHERE first_name LIKE :first AND last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)
}
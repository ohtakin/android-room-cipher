package com.tioh.roomcipher

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import lombok.ToString

@ToString
@Entity(tableName = "TB_USER")
data class User(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "first_name") val firstName: String?,
    @ColumnInfo(name = "last_name") val lastName: String?
)


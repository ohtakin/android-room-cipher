package com.tioh.roomcipher

import android.content.Context
import android.text.Editable
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.commonsware.cwac.saferoom.SafeHelperFactory
import com.tioh.roomcipher.AppDatabase.Companion.DATABASE_CIPHER
import com.tioh.roomcipher.AppDatabase.Companion.DATABASE_NAME

@Database(entities = [User::class], version = 1)

abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao


    companion object : SingletonHolder<AppDatabase, Context>({
        val factory = SafeHelperFactory.fromUser(Editable.Factory.getInstance().newEditable(DATABASE_CIPHER))
        Room.databaseBuilder(it.applicationContext, AppDatabase::class.java, DATABASE_NAME)
            .openHelperFactory(factory)
            .build()
    }) {
        const val DATABASE_CIPHER = "CIPHER"
        private const val DATABASE_NAME = "SQLCipherRoomDatabase"
    }
}
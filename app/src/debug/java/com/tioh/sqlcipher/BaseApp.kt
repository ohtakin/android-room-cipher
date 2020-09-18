package com.tioh.sqlcipher

import android.app.Application
import com.facebook.stetho.Stetho
import com.facebook.stetho.dumpapp.DumperPlugin
import com.facebook.stetho.inspector.protocol.ChromeDevtoolsDomain
import com.sch.stetho.sqlcipher.DatabasePasswordProvider
import com.sch.stetho.sqlcipher.SqlCipherDatabaseDriver
import com.tioh.roomcipher.AppDatabase.Companion.DATABASE_CIPHER

open class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()

        Stetho.initialize(object : Stetho.Initializer(applicationContext) {
            override fun getDumperPlugins(): Iterable<DumperPlugin>? {
                return Stetho.DefaultDumperPluginsBuilder(applicationContext).finish()
            }

            override fun getInspectorModules(): Iterable<ChromeDevtoolsDomain>? {
                val databasePasswordProvider = DatabasePasswordProvider { DATABASE_CIPHER }
                return Stetho.DefaultInspectorModulesBuilder(applicationContext)
                    .provideDatabaseDriver(
                        SqlCipherDatabaseDriver(
                            applicationContext,
                            databasePasswordProvider
                        )
                    )
                    .finish()
            }
        })
    }
}
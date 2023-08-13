package com.example.eom_fe.roomDB

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [AlarmDataModel::class],
    version = 2
)

abstract class AlarmDB: RoomDatabase(){
    abstract fun alarmDao(): AlarmDao

    companion object {
        private  var INSTANCE: AlarmDB? = null

        fun getDatabase(context: Context): AlarmDB {
            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }

            Log.d("eyesonme-ADB", "getDatabase excuted")

            val instance = Room.databaseBuilder(
                context,
                AlarmDB::class.java,
                "alarmdb"
            ).addMigrations(migration_1_2).build()

            INSTANCE = instance
            return instance
        }
    }
}

val migration_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "ALTER TABLE Sound Add COLUMN isChecked INTEGER NOT NULL DEFAULT 0"
        )
    }

}

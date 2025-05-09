package com.dellas0095.assesmobpro2.database.Blushly

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.dellas0095.assesmobpro2.model.Pelanggan

@Database(entities = [Pelanggan::class], version = 2, exportSchema = false)
abstract class PelangganDb : RoomDatabase() {

    abstract val dao: PelangganDao

    companion object {

        @Volatile
        private var INSTANCE: PelangganDb? = null

        // Migrasi dari versi 1 ke versi 2: Tambahkan kolom isDeleted
        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "ALTER TABLE event ADD COLUMN isDeleted INTEGER NOT NULL DEFAULT 0"
                )
            }
        }

        fun getInstance(context: Context): PelangganDb {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PelangganDb::class.java,
                        "event.db"
                    )
                        .addMigrations(MIGRATION_1_2) // Tambahkan migrasi di sini
                        .build()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}
package com.dellas0095.assesmobpro2.database.Blushly

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dellas0095.assesmobpro2.model.Blushly

@Database(entities = [Blushly::class], version = 1, exportSchema = false)
abstract class BlushlyDb : RoomDatabase() {

    abstract val dao: BlushlyDao
    companion object {
        @Volatile
        private var INSTANCE: BlushlyDb? = null

        fun getInstance(context: Context): BlushlyDb {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        BlushlyDb::class.java,
                        "blushly.db"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
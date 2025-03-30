package com.example.partie1

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Utilisateur::class, Planning::class], version = 4)
abstract class AppDB : RoomDatabase() {
    abstract fun utilisateurDao(): UtilisateurDao
    abstract fun planningDao(): PlanningDao

    companion object {
        @Volatile
        private var INSTANCE: AppDB? = null

        fun getInstance(context: Context): AppDB {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDB::class.java,
                    "ma_base_utilisateur"
                )
                    .fallbackToDestructiveMigration().build().also {
                    INSTANCE = it
                }
            }
        }
    }
}

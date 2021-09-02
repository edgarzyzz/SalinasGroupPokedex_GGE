package com.gogaedd.salinasgrouppokedex_gge.persistence.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gogaedd.salinasgrouppokedex_gge.model.PokemonResult


@Database(entities = arrayOf(PokemonResult::class), version = 1)
abstract class AppDatabase() : RoomDatabase() {
    abstract fun pokemonResultDao(): PokemonResultDao


    companion object {
        private var INSTANCE: AppDatabase? = null


        fun getDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java,"appDb")
                    .build()
            }
            return INSTANCE!!

        }
    }
}
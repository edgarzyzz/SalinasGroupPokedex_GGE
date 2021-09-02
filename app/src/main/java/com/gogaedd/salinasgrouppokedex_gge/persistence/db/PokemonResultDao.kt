package com.gogaedd.salinasgrouppokedex_gge.persistence.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gogaedd.salinasgrouppokedex_gge.model.PokemonResult

@Dao
interface PokemonResultDao {


    @Query("Select * From PokemonResult")
    fun getAll() : MutableList<PokemonResult>


    @Insert
    fun insertAll(vararg  pokemons: PokemonResult)

    @Insert(onConflict = OnConflictStrategy.IGNORE )
    fun insert(pokemons: PokemonResult)




}
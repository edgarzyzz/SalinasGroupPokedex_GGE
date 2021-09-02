package com.gogaedd.salinasgrouppokedex_gge.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.gogaedd.salinasgrouppokedex_gge.model.PokemonResult
import com.gogaedd.salinasgrouppokedex_gge.repository.MainRepository

class MainViewModel(application: Application): AndroidViewModel(application) {
    private val repository = MainRepository(application)

    private var lvdPokemons = MutableLiveData<MutableList<PokemonResult>>()

    fun requestAllpokemons(){
        repository.storeAllPokemons()
    }

    fun loadAllPokemons() {
        lvdPokemons.value = repository.getAllPokemons()

    }


}
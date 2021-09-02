package com.gogaedd.salinasgrouppokedex_gge.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.gogaedd.salinasgrouppokedex_gge.model.PokemonDetail
import com.gogaedd.salinasgrouppokedex_gge.model.PokemonResult
import com.gogaedd.salinasgrouppokedex_gge.repository.MainRepository

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = MainRepository(application)

    var lvdPokemons = MutableLiveData<MutableList<PokemonResult>>(mutableListOf())
    var lvdIsFinishLoadData = MutableLiveData<Boolean>()

    var lvdCurrentPoke = MutableLiveData<PokemonResult>()
    var lvdCurrentPokeImage = MutableLiveData<String>()
    var lvdCurrentDetail = MutableLiveData<PokemonDetail>()



    fun setDetail(detail: PokemonDetail?){
        lvdCurrentDetail.value =detail
    }

    fun setStateload(isFinishLoadData: Boolean) {
        lvdIsFinishLoadData.postValue( isFinishLoadData)
    }

    fun setPokemons(all: MutableList<PokemonResult>) {
        lvdPokemons.postValue(all)
    }


    fun setPokemonSelected(pokemon: PokemonResult) {
        lvdCurrentPoke.value = pokemon
    }
    fun nextPoke(){
        lvdPokemons.value?.let{
            if (it.isEmpty()){
                return
            }else{
                var i = getCurrentPos()
                if (i>=it.size){
                    i=0
                }
                setPokemonSelected(it.get(i))
            }
        }


    }

    fun previousPoke(){
        lvdPokemons.value?.let{
            if (it.isEmpty()){
                return
            }else{
                var pos = getCurrentPos() - 2
                if (pos<0){
                    pos=it.size-1
                }
                setPokemonSelected(it.get(pos))
            }
        }
    }

    private fun getCurrentPos(): Int {
        return lvdCurrentPoke.value?.let {
            it.uid
        }?:run{
            0
        }
    }

    fun setCurrentImage(frontDefault: String) {
        lvdCurrentPokeImage.value = frontDefault
    }


}
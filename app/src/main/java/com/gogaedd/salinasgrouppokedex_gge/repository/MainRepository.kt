package com.gogaedd.salinasgrouppokedex_gge.repository

import android.app.Application
import android.content.Context
import com.gogaedd.salinasgrouppokedex_gge.model.PokemonContainer
import com.gogaedd.salinasgrouppokedex_gge.model.PokemonResult
import com.gogaedd.salinasgrouppokedex_gge.persistence.db.AppDatabase
import com.gogaedd.salinasgrouppokedex_gge.persistence.net.ApiClient
import com.gogaedd.salinasgrouppokedex_gge.persistence.net.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainRepository(application: Application) {
    private val db = AppDatabase.getDatabase(application)
    private val pokemonResultDao = db.pokemonResultDao()






    fun storeAllPokemons() {

        val apiService = ApiClient.getRetrofit().create(ApiService::class.java)
        val call = apiService.getPokemonContainer("500")
        call.enqueue(object : Callback<PokemonContainer> {
            override fun onResponse(
                call: Call<PokemonContainer>,
                response: Response<PokemonContainer>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {


                        runBlocking (Dispatchers.IO){
                            for (pokemon in it.results){
                                pokemon.uid = pokemon.getId()

                                pokemonResultDao.insert(pokemon)

                            }
                        }


                    } ?: run { }
                } else {

                }
            }

            override fun onFailure(call: Call<PokemonContainer>, t: Throwable) {

            }
        })


    }

    fun getAllPokemons() =

        pokemonResultDao.getAll()


}


//    fun searchAllpokemosn2() = liveData<MutableList<PokemonResult>?>(Dispatchers.IO) {
//        val apiService = ApiClient.getRetrofit().create(ApiService::class.java)
//        val call = apiService.getPokemonContainer("500")
//
//        if (call.isSuccessful) {
//            val body = call.body()
//            body!!.results.size
//            emit(body!!.results)
//
//
//        } else {
//            emit(null)
//        }
//    }




//    public  fun searchAllpokemosn(){
//        CoroutineScope(Dispatchers.IO).launch {
//            val apiService = ApiClient.getRetrofit().create(ApiService::class.java)
//            val call = apiService.getPokemonContainer("500")
//
//            if (call.isSuccessful){
//                val body = call.body()
//                body!!.results.size
//            }else{
//
//            }
//        }
//
//    }

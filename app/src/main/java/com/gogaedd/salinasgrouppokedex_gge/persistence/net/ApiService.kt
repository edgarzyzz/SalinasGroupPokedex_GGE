package com.gogaedd.salinasgrouppokedex_gge.persistence.net

import com.gogaedd.salinasgrouppokedex_gge.model.PokemonContainer
import com.gogaedd.salinasgrouppokedex_gge.model.PokemonDetail
import com.gogaedd.salinasgrouppokedex_gge.model.ResponsePokeImage
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {


    @GET("api/v2/pokemon")
    fun getPokemonContainer(@Query("limit") limit:String): Call<PokemonContainer>
//    suspend fun getPokemonContainer(@Query("limit") limit:String): Response<PokemonContainer>


    @GET("https://pokeapi.co/api/v2/pokemon-form/{id}/")
    fun getObjectImage(@Path("id") id:Int): Call<ResponsePokeImage>


    @GET("api/v2/pokemon/{id}/")
    fun getDetail(@Path("id") id:Int): Call<PokemonDetail>

}
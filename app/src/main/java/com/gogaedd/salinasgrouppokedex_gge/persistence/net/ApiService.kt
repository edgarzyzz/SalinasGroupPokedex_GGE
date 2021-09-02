package com.gogaedd.salinasgrouppokedex_gge.persistence.net

import com.gogaedd.salinasgrouppokedex_gge.model.PokemonContainer
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    @GET("api/v2/pokemon")
    fun getPokemonContainer(@Query("limit") limit:String): Call<PokemonContainer>
//    suspend fun getPokemonContainer(@Query("limit") limit:String): Response<PokemonContainer>
}
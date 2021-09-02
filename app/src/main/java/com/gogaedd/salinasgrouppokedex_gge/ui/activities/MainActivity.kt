package com.gogaedd.salinasgrouppokedex_gge.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.gogaedd.salinasgrouppokedex_gge.R
import com.gogaedd.salinasgrouppokedex_gge.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



//        val url = "https://pokeapi.co/api/v2/pokemon/1/"





        /*pokemonContainer.enqueue(object: Callback<PokemonContainer>{
            override fun onResponse(
                call: Call<PokemonContainer>,
                response: Response<PokemonContainer>
            ) {

            }

            override fun onFailure(call: Call<PokemonContainer>, t: Throwable) {
                Log.d("TAG", "onResponse: ")
            }
        })*/
    }
}
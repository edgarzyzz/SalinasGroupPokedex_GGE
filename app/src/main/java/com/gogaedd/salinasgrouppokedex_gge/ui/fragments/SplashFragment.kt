package com.gogaedd.salinasgrouppokedex_gge.ui.fragments

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.gogaedd.salinasgrouppokedex_gge.R
import com.gogaedd.salinasgrouppokedex_gge.model.PokemonContainer
import com.gogaedd.salinasgrouppokedex_gge.model.ResponsePokeImage
import com.gogaedd.salinasgrouppokedex_gge.persistence.db.AppDatabase
import com.gogaedd.salinasgrouppokedex_gge.persistence.net.ApiClient
import com.gogaedd.salinasgrouppokedex_gge.persistence.net.ApiService
import com.gogaedd.salinasgrouppokedex_gge.viewmodel.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class SplashFragment : Fragment() {

    lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        getInfo()

        viewModel.lvdIsFinishLoadData.observe(viewLifecycleOwner,observerLoadData)

    }




    private fun getInfo(){
        val db = AppDatabase.getDatabase(requireContext())
        val pokemonResultDao = db.pokemonResultDao()

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
                                viewModel.setStateload(true)
                            }
                        }




                    } ?: run {
                        viewModel.setStateload(false)
                    }
                } else {
                    viewModel.setStateload(false)
                }
            }

            override fun onFailure(call: Call<PokemonContainer>, t: Throwable) {
                viewModel.setStateload(false)
            }
        })
    }


    val observerLoadData= Observer<Boolean>{
        if (it){
                        val action = SplashFragmentDirections.actionSplashFragmentToAllPokemonsFragment()
            findNavController().navigate(action)
        }else{
            Toast.makeText(requireContext(), "Error", Toast.LENGTH_LONG).show()
        }
    }


}
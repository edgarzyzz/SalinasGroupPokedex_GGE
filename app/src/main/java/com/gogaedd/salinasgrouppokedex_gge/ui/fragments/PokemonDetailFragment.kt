package com.gogaedd.salinasgrouppokedex_gge.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gogaedd.salinasgrouppokedex_gge.databinding.FragmentPokemonDetailBinding
import com.gogaedd.salinasgrouppokedex_gge.model.PokemonDetail
import com.gogaedd.salinasgrouppokedex_gge.model.PokemonResult
import com.gogaedd.salinasgrouppokedex_gge.model.ResponsePokeImage
import com.gogaedd.salinasgrouppokedex_gge.persistence.net.ApiClient
import com.gogaedd.salinasgrouppokedex_gge.persistence.net.ApiService
import com.gogaedd.salinasgrouppokedex_gge.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_pokemon_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PokemonDetailFragment : Fragment() {
    lateinit var viewModel : MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        val binding = FragmentPokemonDetailBinding.inflate(inflater,container,false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.lvdCurrentPoke.observe(viewLifecycleOwner,observerCurrentPoke)
        viewModel.lvdCurrentDetail.observe(viewLifecycleOwner,observerPokeDetail)

    }



    val observerCurrentPoke = Observer<PokemonResult>{

        requestImage(it.uid)
        requestDetail(it.uid)

    }


    private fun requestImage(id:Int){
        val apiService = ApiClient.getRetrofit().create(ApiService::class.java)
        val call = apiService.getObjectImage(id)
        call.enqueue(object : Callback<ResponsePokeImage> {
            override fun onResponse(call: Call<ResponsePokeImage>, response: Response<ResponsePokeImage>) {
                Log.d("TAG", "onResponse: ")
                if (response.isSuccessful){
                    val body = response.body()
                    body?.let {
                        viewModel.setCurrentImage(it.sprites.front_default)

                    }?: run{
                        viewModel.setCurrentImage("")
                    }
                }else{
                    viewModel.setCurrentImage("")
                }




            }

            override fun onFailure(call: Call<ResponsePokeImage>, t: Throwable) {
                viewModel.setCurrentImage("")
            }
        })
    }


    val observerPokeDetail = Observer<PokemonDetail?>{ it ->
        if (it == null)return@Observer

        var abilities = ""
        it.abilities.forEach{ ab->

                abilities += "${ab.ability.name}   "

        }

        var types = ""
        it.types.forEach{ ty->
            types += " ${ty.type.name}"
        }
        tvInfoAbilities.text = abilities
        tvInfoexperience.text = it.base_experience.toString()
        tvInfoHeight.text = it.height.toString()
        tvInfoWeight.text = it.weight.toString()
        tvInfoTypes.text = types
    }
    private fun requestDetail(id:Int) {
        val apiService = ApiClient.getRetrofit().create(ApiService::class.java)
        val detail = apiService.getDetail(id)
        detail.enqueue(object: Callback<PokemonDetail>{
            override fun onResponse(call: Call<PokemonDetail>, response: Response<PokemonDetail>) {
                if (response.isSuccessful){
                    viewModel.setDetail(response.body()!!)
                    return
                }
                viewModel.setDetail(null)

            }

            override fun onFailure(call: Call<PokemonDetail>, t: Throwable) {
                Log.d("TAG", "onFailure: ")
                viewModel.setDetail(null)
            }
        })
    }


}
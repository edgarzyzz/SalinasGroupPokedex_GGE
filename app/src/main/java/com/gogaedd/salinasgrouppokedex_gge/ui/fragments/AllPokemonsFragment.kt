package com.gogaedd.salinasgrouppokedex_gge.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.gogaedd.salinasgrouppokedex_gge.R
import com.gogaedd.salinasgrouppokedex_gge.adapters.PokemonResultAdapter
import com.gogaedd.salinasgrouppokedex_gge.databinding.FragmentAllPokemonsBinding
import com.gogaedd.salinasgrouppokedex_gge.databinding.ItemPokemonResultBinding
import com.gogaedd.salinasgrouppokedex_gge.model.PokemonResult
import com.gogaedd.salinasgrouppokedex_gge.persistence.db.AppDatabase
import com.gogaedd.salinasgrouppokedex_gge.repository.MainRepository
import com.gogaedd.salinasgrouppokedex_gge.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_all_pokemons.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking


class AllPokemonsFragment : Fragment(), PokemonResultAdapter.PokemonResultListener {

    lateinit var viewmodel: MainViewModel
    val  pokeAdapter =PokemonResultAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAllPokemonsBinding.inflate(inflater, container, false)
        viewmodel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        binding.viewmodel = viewmodel
        binding.lifecycleOwner = this
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)





        runBlocking(Dispatchers.IO) {
            val db = AppDatabase.getDatabase(requireContext())
            val all = db.pokemonResultDao().getAll()
            if (!all.isNullOrEmpty()) {
                viewmodel.setPokemons(all)
            }
        }
        pokeAdapter.setlistener(this)
        rvPokemonsResult.apply {


            adapter = pokeAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }


    }

    override fun onClickPokemonresult(pokemon: PokemonResult) {
        viewmodel.setPokemonSelected(pokemon)
        val action = AllPokemonsFragmentDirections.actionAllPokemonsFragmentToPokemonDetailFragment()
        findNavController().navigate(action)
    }


}
package com.gogaedd.salinasgrouppokedex_gge.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.gogaedd.salinasgrouppokedex_gge.R
import com.gogaedd.salinasgrouppokedex_gge.databinding.FragmentPokemonDetailBinding
import com.gogaedd.salinasgrouppokedex_gge.viewmodel.MainViewModel


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








}
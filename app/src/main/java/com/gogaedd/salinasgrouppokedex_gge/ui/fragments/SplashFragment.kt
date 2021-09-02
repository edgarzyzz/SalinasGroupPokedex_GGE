package com.gogaedd.salinasgrouppokedex_gge.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.gogaedd.salinasgrouppokedex_gge.R
import com.gogaedd.salinasgrouppokedex_gge.repository.MainRepository
import com.gogaedd.salinasgrouppokedex_gge.viewmodel.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking


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
        viewModel.requestAllpokemons()
//
        runBlocking(Dispatchers.Default) {
            delay(10000)
            val action =
                SplashFragmentDirections.actionSplashFragmentToAllPokemonsFragment()
            findNavController().navigate(action)
        }

    }


}
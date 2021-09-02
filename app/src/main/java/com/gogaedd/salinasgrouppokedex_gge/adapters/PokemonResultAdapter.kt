package com.gogaedd.salinasgrouppokedex_gge.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gogaedd.salinasgrouppokedex_gge.databinding.ItemPokemonResultBinding
import com.gogaedd.salinasgrouppokedex_gge.model.PokemonResult

class PokemonResultAdapter(): RecyclerView.Adapter<PokemonResultAdapter.PokemonResultViewHolder>() {

    val mListPokemons : MutableList<PokemonResult> = mutableListOf()
    var mlistener : PokemonResultListener? = null


    fun setlistener(listener : PokemonResultListener){
        mlistener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonResultViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPokemonResultBinding.inflate(inflater,parent,false)
        return PokemonResultViewHolder(binding)

    }

    override fun onBindViewHolder(holder: PokemonResultViewHolder, position: Int)  = holder.bind(mListPokemons[position],mlistener)

    override fun getItemCount(): Int =mListPokemons.size
    fun updateElements(data: MutableList<PokemonResult>) {
        mListPokemons.clear()
        mListPokemons.addAll(data)
        notifyDataSetChanged()
    }

    class PokemonResultViewHolder(val binding : ItemPokemonResultBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(poke: PokemonResult, mlistener: PokemonResultListener?){
            binding.pokemonResult = poke

            binding.root.setOnClickListener{
                mlistener?.onClickPokemonresult(poke)
            }


        }

    }

    interface PokemonResultListener {
        fun onClickPokemonresult(pokemon:PokemonResult)
    }
}
package com.gogaedd.salinasgrouppokedex_gge.utils.binding_adapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gogaedd.salinasgrouppokedex_gge.adapters.PokemonResultAdapter
import com.gogaedd.salinasgrouppokedex_gge.model.PokemonResult

object BindingAdapterRecyclerView {


    @JvmStatic
    @BindingAdapter("data")
    fun setData(rv: RecyclerView, data: MutableList<PokemonResult>) {
        if (data.isNullOrEmpty()) return
        rv.adapter?.let {
            if (it is PokemonResultAdapter){
                it.updateElements(data)
            }
        }

    }
}
package com.gogaedd.salinasgrouppokedex_gge.model

import com.google.gson.annotations.SerializedName

data class PokemonDetail(
    var abilities: MutableList<AbilityParent> = mutableListOf(),
    var base_experience: Int = 0,
    var height:Int =0,
    var types : MutableList<Types> = mutableListOf(),
    var weight: Int = 0



)

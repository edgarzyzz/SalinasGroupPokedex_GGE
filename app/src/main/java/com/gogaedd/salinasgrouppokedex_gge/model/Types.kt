package com.gogaedd.salinasgrouppokedex_gge.model

import com.google.gson.annotations.SerializedName

data class Types(
    @SerializedName("type")
    var type :Type = Type()
)

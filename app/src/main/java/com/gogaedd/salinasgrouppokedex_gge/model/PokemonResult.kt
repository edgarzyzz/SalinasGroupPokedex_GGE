package com.gogaedd.salinasgrouppokedex_gge.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class PokemonResult(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var uid: Int = 0,


    @SerializedName("name")
    @ColumnInfo(name = "name")
    val name: String = "",

    @SerializedName("url")
    @ColumnInfo(name = "url")
    val url: String = ""


) {
    fun getId(): Int {
        if (url.isNotEmpty() && url.contains("/")) {
            val split = url.split("/")

            if (split.size > 2) {
                val id = split[split.size - 2]
                return try {
                    id.toInt()
                } catch (e: Exception) {
                    0
                }

            }
        }
        return 0

    }

}
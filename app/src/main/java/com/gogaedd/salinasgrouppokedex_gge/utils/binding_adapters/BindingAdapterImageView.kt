package com.gogaedd.salinasgrouppokedex_gge.utils.binding_adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.gogaedd.salinasgrouppokedex_gge.R


object BindingAdapterImageView {

    @JvmStatic
    @BindingAdapter("drawImageUrl")
    fun drawImageUrl(imgview: ImageView, url:String){
        if (url.isNullOrEmpty()) return
        val options: RequestOptions = RequestOptions()
            .centerCrop()
            .placeholder(R.drawable.ic_pokeball)
            .error(R.drawable.ic_pokeball)



        Glide.with(imgview.context).load(url).apply(options).into(imgview)
    }
}
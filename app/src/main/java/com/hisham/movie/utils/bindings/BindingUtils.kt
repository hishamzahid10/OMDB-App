package com.hisham.movie.utils.bindings

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

object BindingUtils{
@BindingAdapter("imageSrc")
@JvmStatic

fun setImageSrc(imageView: ImageView,imageSrc:String?){
    with(imageView){
        Glide.with(context)
            .load(imageSrc)
            .thumbnail(0.1f)
            .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)
                )
            .into(imageView)
    }
}


    @BindingAdapter("imageSrcInt")
    @JvmStatic
    fun setImageSrc(imageView: ImageView,resource:Int){
        imageView.setImageResource(resource)

    }

}
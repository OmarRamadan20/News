package com.example.news.News

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.news.R

@BindingAdapter("app:url")
fun urlToImage(imageView: ImageView,url:String){
    val options = RequestOptions()
        .placeholder(R.drawable.image_processing)
        .error(R.drawable.image_processing)
    Glide.with(imageView).load(url).apply(options).into(imageView)
}
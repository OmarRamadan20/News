package com.example.news.News

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("app:url")
fun urlToImage(imageView: ImageView,url:String){
    Glide.with(imageView).load(url).into(imageView)
}
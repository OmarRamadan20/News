package com.example.news

class ViewMessage(
    val message:String,
    val posActionName:String?=null,
    val posAction: (() -> Unit)?=null,
    val negActionName:String?=null,
    val negAction:(() -> Unit)?=null,
    val isDissmisable:Boolean=true
    )
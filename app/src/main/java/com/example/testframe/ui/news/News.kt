package com.example.newsapp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class News(@SerializedName("status")
                @Expose
                val status:String,
                @SerializedName("totalResult")
                @Expose
                val totalResult:Int,
                @SerializedName("articles")
                @Expose
                val article:List<Article>)
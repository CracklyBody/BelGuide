package com.example.newsapp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Source(@SerializedName("id") @Expose private val id:String,
                  @SerializedName("name") @Expose public val name:String)
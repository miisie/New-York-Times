package com.example.articleview.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class retrofit {
    companion object{
        private var retrofit: Retrofit?=null
        val client:Retrofit get() {
            if(retrofit==null){
                retrofit=Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://api.nytimes.com/svc/")
                    .build()
            }
            return retrofit!!
        }
    }
}
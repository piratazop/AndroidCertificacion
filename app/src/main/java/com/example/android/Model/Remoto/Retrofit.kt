package com.example.android.Model.Remoto

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retrofit {

    companion object {

        private const val BASE_URL = "https://jp-conciertos.onrender.com/"

        lateinit var retrofitInstance: Retrofit
        fun getRetrofit(): Api {
            val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
                GsonConverterFactory.create()
            ).build()
            return retrofit.create(Api::class.java)
        }
    }



}
package com.example.networkdemo.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetHandler {

    companion object {
        private val retrofit: Retrofit by lazy {
            with(Retrofit.Builder()) {
                baseUrl("http://192.168.1.93:3000")
                addConverterFactory(GsonConverterFactory.create())
                build()
            }
        }

        val authApi: AuthApi by lazy {
            retrofit.create(AuthApi::class.java)
        }

        val cardsApi: CardsApi by lazy {
            retrofit.create(CardsApi::class.java)
        }
    }

}
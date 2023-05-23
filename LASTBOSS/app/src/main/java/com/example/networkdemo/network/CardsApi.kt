package com.example.networkdemo.network

import com.example.networkdemo.CardsResponse
import retrofit2.http.GET
interface  CardsApi {
    @GET("/cards")
    suspend fun getAll(): CardsResponse
}
package com.example.networkdemo.network

import com.example.networkdemo.AuthBody
import com.example.networkdemo.AuthResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("/registration")
    suspend fun register(@Body body: AuthBody): AuthResponse
}
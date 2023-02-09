package com.thepparat.roanldowithktor.getRonaldo.data.api

import com.thepparat.roanldowithktor.getRonaldo.data.dto.Ronaldo
import retrofit2.http.GET

interface RonaldoApi {
    @GET("/randomRonaldo")
    suspend fun getRandomRonaldo() : Ronaldo

    companion object {
        const val BASE_URL = "http://172.20.10.2:8080"
    }
}


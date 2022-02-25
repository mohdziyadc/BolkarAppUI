package com.example.bolkarappui.network

import com.example.bolkarappui.models.Bolkar
import com.example.bolkarappui.models.Data
import retrofit2.Response
import retrofit2.http.GET

interface BolkarAPI {

    @GET("/live/room.json")
    suspend fun getData(): Response<Bolkar>


}
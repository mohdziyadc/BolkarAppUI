package com.example.bolkarappui.repository

import com.example.bolkarappui.network.RetrofitInstance

class BolkarRespository {

    suspend fun getData() = RetrofitInstance.api.getData()
}
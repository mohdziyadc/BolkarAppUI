package com.example.bolkarappui.repository

import com.example.bolkarappui.network.RetrofitInstance

class BolkarRepository {

    suspend fun getData() = RetrofitInstance.api.getData()
}
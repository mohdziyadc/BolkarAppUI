package com.example.bolkarappui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.bolkarappui.models.*
import com.example.bolkarappui.repository.BolkarRespository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class BolkarViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = BolkarRespository()

    val speakersList = MutableLiveData<MutableList<Speaker>>()
    val membersList = MutableLiveData<List<Member>>()
    fun getData() = viewModelScope.launch {
        val response = try {
            repository.getData()

        } catch (e: HttpException) {
            Log.d("BolkarVM", "getData: ${e.message} ")
            return@launch
        } catch (e: IOException) {
            Log.d("BolkarVM", "getData: ${e.message} ")
            return@launch
        }
        Log.d("BolkarVM", "getData: ${response.body()} ")
        if (response.isSuccessful) {
            response.body()?.let {
                val tempSpeakersList = it.data.speakers.toMutableList()
                val host = it.data.host
                tempSpeakersList.add(0, Speaker(host._id, host.viewType, host.au, host.mic, host.mod, host.n,host.socketid, host.u, host.visible))

                speakersList.postValue(tempSpeakersList)
                membersList.postValue(it.data.members)

                Log.d("BolkarVM", "getData: ${it} ")
            }


        }

    }
}
package com.example.submission1androidnouval.fragmen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.submission1androidnouval.data.response.Event
import com.example.submission1androidnouval.data.response.JustResponse
import com.example.submission1androidnouval.data.retrofit.ApiConfig
import kotlinx.coroutines.launch
import retrofit2.Response

class FinishedViewModel : ViewModel() {
    private val finishedEvent = MutableLiveData<List<Event>>()
    val eventFinished : LiveData<List<Event>> = finishedEvent
    private val loading = MutableLiveData<Boolean>()
    val load : LiveData<Boolean> = loading

    init {
        fetchFinishedEvent()
    }
    private fun fetchFinishedEvent() = viewModelScope.launch {
        loading.value = true
        try {
            val response: Response<JustResponse> = ApiConfig.getApiService().getEvents(active = 0)
            if (response.isSuccessful) {
                loading.value = false
               finishedEvent.value = response.body()?.event ?: listOf()
            } else {
                loading.value = false
                finishedEvent.value = listOf()
            }
        } catch (e: Exception) {
            loading.value = false
            finishedEvent.value = listOf()
        }
    }

}

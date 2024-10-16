package com.example.submission1androidnouval.fragmen
import com.example.submission1androidnouval.data.response.JustResponse
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.submission1androidnouval.data.response.Event
import com.example.submission1androidnouval.data.retrofit.ApiConfig
import kotlinx.coroutines.launch
import retrofit2.Response

class UpcomingViewmodel : ViewModel() {
private val upComingEvent = MutableLiveData<List<Event>>()
    val upcomingEvent : LiveData<List<Event>> = upComingEvent
private val loading = MutableLiveData<Boolean>()
val load : LiveData<Boolean> = loading

init {
fetchUpcomingEvent()
}
private fun fetchUpcomingEvent() = viewModelScope.launch {
    loading.value = true
    try {
        val response: Response<JustResponse> = ApiConfig.getApiService().getEvents(active = 1)
        if (response.isSuccessful) {
loading.value = false
upComingEvent.value = response.body()?.event ?: listOf()
            } else {
loading.value = false
upComingEvent.value = listOf()
        }
    } catch (e: Exception) {
        loading.value = false
        upComingEvent.value = listOf()
        }
    }

}

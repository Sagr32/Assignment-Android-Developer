package com.sakr.assignment.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sakr.assignment.data.remote.ApiStatus
import com.sakr.assignment.data.repository.MainRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: MainRepository) : ViewModel() {

    sealed class NewsEvent {
        class Success<T>(val result: T) : NewsEvent()
        class Failure(val errorText: String) : NewsEvent()
        object Loading : NewsEvent()
        object Empty : NewsEvent()
    }

    private val _headlines = MutableStateFlow<NewsEvent>(NewsEvent.Empty)
    val headlines: StateFlow<NewsEvent> = _headlines

    private val _sources = MutableStateFlow<NewsEvent>(NewsEvent.Empty)
    val sources: StateFlow<NewsEvent> = _sources


    private fun getHeadlines() = viewModelScope.launch {
        _headlines.value = NewsEvent.Loading
        when (val headlinesResponse = repository.getHeadlines("eg")) {
            is ApiStatus.Error -> _headlines.value = NewsEvent.Failure(headlinesResponse.message!!)
            is ApiStatus.Success -> {
                val headlineData = headlinesResponse.data!!
                _headlines.value = NewsEvent.Success(
                    headlineData
                )
            }
            else -> {}
        }
    }

    private fun getSources() = viewModelScope.launch {
        _sources.value = NewsEvent.Loading
        when (val sourcesResponse = repository.getHeadlines("eg")) {
            is ApiStatus.Error -> _sources.value = NewsEvent.Failure(sourcesResponse.message!!)
            is ApiStatus.Success -> {
                val sourcesData = sourcesResponse.data!!
                _sources.value = NewsEvent.Success(
                    sourcesData
                )
            }
            else -> {}
        }
    }


}
package com.sakr.assignment.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sakr.assignment.data.local.User
import com.sakr.assignment.data.models.NewsResponse
import com.sakr.assignment.data.models.SourceResponse
import com.sakr.assignment.data.remote.ApiStatus
import com.sakr.assignment.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {
    private val _userInfo = MutableLiveData<User?>()
    val userInfo: MutableLiveData<User?> = _userInfo

    sealed class NewsEvent {
        class Success(val result: NewsResponse) : NewsEvent()
        class Failure(val errorText: String) : NewsEvent()
        object Loading : NewsEvent()
        object Empty : NewsEvent()
    }

    sealed class SourceEvent {
        class Success(val result: SourceResponse) : NewsEvent()
        class Failure(val errorText: String) : NewsEvent()
        object Loading : NewsEvent()
        object Empty : NewsEvent()
    }

    private val _headlines = MutableStateFlow<NewsEvent>(NewsEvent.Empty)
    val headlines: StateFlow<NewsEvent> = _headlines

    private val _sources = MutableStateFlow<NewsEvent>(NewsEvent.Empty)
    val sources: StateFlow<NewsEvent> = _sources


    init {
        getHeadlines()
        getSources()
    }

    private fun getHeadlines() = viewModelScope.launch {
        Timber.tag("getHeadlines").d("start")
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
        _sources.value = SourceEvent.Loading
        when (val sourcesResponse = repository.getSources()) {
            is ApiStatus.Error -> _sources.value = SourceEvent.Failure(sourcesResponse.message!!)
            is ApiStatus.Success -> {
                val sourcesData = sourcesResponse.data!!
                _sources.value = SourceEvent.Success(
                    sourcesData
                )
            }
            else -> {}
        }
    }

    fun saveUser(user: User) {
        viewModelScope.launch {
            repository.insertUser(user)
        }
    }

    fun checkUserDetails(email: String, password: String){
        viewModelScope.launch {
            val userDetails = repository.checkUser(email, password)
            if (userDetails?.id != null) {
                _userInfo.value= userDetails
            } else {
                _userInfo.value= null

            }

        }

    }

}
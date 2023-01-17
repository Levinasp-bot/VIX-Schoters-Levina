package com.example.githubproject.viewModel

import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubproject.ResponseUser
import com.example.githubproject.api.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val resultUser = MutableLiveData<Results>()

    fun getUser() {
        viewModelScope.launch {
            launch(Dispatchers.Main){
                flow {
                    val response = ApiClient
                        .apiService
                        .getUserGithub()

                    emit(response)
                }.onStart {
                resultUser.value = Results.Loading(true)
                }.onCompletion {
                    resultUser.value = Results.Loading(false)
                }.catch {
                    Log.e("Error", it.message.toString())
                    it.printStackTrace()
                    resultUser.value = Results.Error(it)
                }.collect {
                    resultUser.value = Results.Success(it)
                }
            }

        }
    }
}
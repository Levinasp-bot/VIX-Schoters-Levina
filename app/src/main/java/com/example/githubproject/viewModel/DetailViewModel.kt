package com.example.githubproject.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubproject.api.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel(){
    val resultDetailUser = MutableLiveData<Results>()
    val resultFollowers = MutableLiveData<Results>()
    val resultFollowing = MutableLiveData<Results>()

    fun getDetailUser(username: String) {
        viewModelScope.launch {
            launch(Dispatchers.Main){
                flow {
                    val response = ApiClient
                        .apiService
                        .getDetailUserGithub(username)

                    emit(response)
                }.onStart {
                    resultDetailUser.value = Results.Loading(true)
                }.onCompletion {
                    resultDetailUser.value = Results.Loading(false)
                }.catch {
                    Log.e("Error", it.message.toString())
                    it.printStackTrace()
                    resultDetailUser.value = Results.Error(it)
                }.collect {
                    resultDetailUser.value = Results.Success(it)
                }
            }

        }
    }
    fun getFollowers(username: String){
        viewModelScope.launch {
            launch(Dispatchers.Main){
                flow {
                    val response = ApiClient
                        .apiService
                        .getFollowersUserGithub(username)

                    emit(response)
                }.onStart {
                    resultFollowers.value = Results.Loading(true)
                }.onCompletion {
                    resultFollowers.value = Results.Loading(false)
                }.catch {
                    Log.e("Error", it.message.toString())
                    it.printStackTrace()
                    resultFollowers.value = Results.Error(it)
                }.collect {
                    resultFollowers.value = Results.Success(it)
                }
            }

        }
    }
    fun getFollowing(username: String){
        viewModelScope.launch {
            launch(Dispatchers.Main){
                flow {
                    val response = ApiClient
                        .apiService
                        .getFollowingUserGithub(username)

                    emit(response)
                }.onStart {
                    resultFollowing.value = Results.Loading(true)
                }.onCompletion {
                    resultFollowing.value = Results.Loading(false)
                }.catch {
                    Log.e("Error", it.message.toString())
                    it.printStackTrace()
                    resultFollowing.value = Results.Error(it)
                }.collect {
                    resultFollowing.value = Results.Success(it)
                }
            }

        }
    }
}
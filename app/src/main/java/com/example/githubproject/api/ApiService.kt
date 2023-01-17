package com.example.githubproject.api

import com.example.githubproject.BuildConfig
import com.example.githubproject.ResponseDetailUser
import com.example.githubproject.ResponseUser
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ApiService {
    @JvmSuppressWildcards
    @GET("users")
    suspend fun getUserGithub(): MutableList<ResponseUser.Items>

    @JvmSuppressWildcards
    @GET("users/{username}")
    suspend fun getDetailUserGithub(@Path("username") username:String): ResponseDetailUser

    @JvmSuppressWildcards
    @GET("/users/{username}/followers")
    suspend fun getFollowersUserGithub(@Path("username") username:String): MutableList<ResponseUser.Items>

    @JvmSuppressWildcards
    @GET("/users/{username}/following")
    suspend fun getFollowingUserGithub(@Path("username") username:String): MutableList<ResponseUser.Items>
}


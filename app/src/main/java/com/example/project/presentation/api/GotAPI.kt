package com.example.project.presentation.api
import retrofit2.Call
import retrofit2.http.GET

interface GotAPI {

    @GET("character")
    fun getCharacterList() : Call<List<GOTResponse>>
}
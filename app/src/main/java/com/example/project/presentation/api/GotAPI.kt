package com.example.project.presentation.api
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GotAPI {

    @GET("character")
    fun getCharacterList() : Call<List<GOTResponse>>

    @GET("character/{{id}}")
    fun getCharacterDetail{@Path("id") id : String}: Call <GOTResponse>
}
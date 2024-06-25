package com.example.kosmoschallange.network

import com.example.kosmoschallange.model.CharacterResponse
import retrofit2.http.GET

interface RickAndMortyApi {
    @GET("character/?page=1")
    suspend fun getCharacters(): CharacterResponse
}
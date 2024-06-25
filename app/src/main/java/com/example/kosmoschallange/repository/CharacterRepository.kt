package com.example.kosmoschallange.repository

import com.example.kosmoschallange.model.RickAndMortyCharacter
import com.example.kosmoschallange.network.RetrofitInstance

class CharacterRepository {
    private val api = RetrofitInstance.api

    suspend fun getCharacters(): List<RickAndMortyCharacter> {
        return api.getCharacters().results
    }
}
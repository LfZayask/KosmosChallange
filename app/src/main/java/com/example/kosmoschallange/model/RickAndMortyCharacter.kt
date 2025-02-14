package com.example.kosmoschallange.model

data class RickAndMortyCharacter(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: Origin,
    val location: Location,
    val image: String
)

data class Origin(
    val name: String,
    val url: String
)


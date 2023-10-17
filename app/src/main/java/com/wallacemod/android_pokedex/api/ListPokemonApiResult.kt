package com.wallacemod.android_pokedex.api

data class ListPokemonApiResult(
    val count: Int,
    val next: String?,
    val previous: String?, //? significa que a propriedade pode aceitar Null
    val results: List<PokemonItemApi>
)
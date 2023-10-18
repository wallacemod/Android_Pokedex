package com.wallacemod.android_pokedex.viewmodel

import androidx.lifecycle.ViewModel
import com.wallacemod.android_pokedex.model.PokemonRepository
import com.wallacemod.android_pokedex.view.list.PokemonItem

class PokemonViewModel : ViewModel() {
    val pokemonItems = PokemonRepository.pokemonItems
}
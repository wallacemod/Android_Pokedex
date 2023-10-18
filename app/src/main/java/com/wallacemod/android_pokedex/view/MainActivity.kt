package com.wallacemod.android_pokedex.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wallacemod.android_pokedex.R
import com.wallacemod.android_pokedex.view.list.PokemonListAdapter
import com.wallacemod.android_pokedex.viewmodel.PokemonViewModel

// POJO -> Plain Old Java Object

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pokemonViewModel = ViewModelProvider(this).get(PokemonViewModel::class.java)
        val pokemonItems = pokemonViewModel.pokemonItems

        val rvPokemon= findViewById<RecyclerView>(R.id.rvPokemon)
        rvPokemon.layoutManager = LinearLayoutManager(this) //Define como vai ser disposto os itens

        pokemonItems.observe(this){
            rvPokemon.adapter = PokemonListAdapter(it.toTypedArray())
        }
    }
}
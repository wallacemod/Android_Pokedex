package com.wallacemod.android_pokedex.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wallacemod.android_pokedex.R
import com.wallacemod.android_pokedex.model.api.ListPokemonApiResult
import com.wallacemod.android_pokedex.model.api.PokeApiService
import com.wallacemod.android_pokedex.view.list.PokemonItem
import com.wallacemod.android_pokedex.view.list.PokemonListAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// POJO -> Plain Old Java Object

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(PokeApiService::class.java)

        val call = service.listPokemon()

        call.enqueue(object : Callback<ListPokemonApiResult> {
            override fun onResponse(
                call: Call<ListPokemonApiResult>,
                response: Response<ListPokemonApiResult>
            ) {
                // Caso a requisição HTTP tenha sido bem sucedida
                Log.d("POKEMON_API", response.body().toString())

                response.body()?.let {

                    val pokemonItems = it.results.mapIndexed() { index, result ->
                        val number = (index + 1).toString().padStart(3,'0')
                        PokemonItem(result.name,
                            "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/$number.png")
                    }.toTypedArray()

                    val rvPokemon= findViewById<RecyclerView>(R.id.rvPokemon)
                    rvPokemon.layoutManager = LinearLayoutManager(this@MainActivity) //Define como vai ser disposto os itens
                    rvPokemon.adapter = PokemonListAdapter(pokemonItems)

                }
            }

            override fun onFailure(call: Call<ListPokemonApiResult>, t: Throwable) {
                // Caso a requisição HTTP tenha falhado
                Log.e("POKEMON_API", "Erro ao carregar API.", t)
            }
        })
    }
}
package com.wallacemod.android_pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import retrofit2.Call
import retrofit2.http.GET

data class ListPokemonResult(
    val nome: String,
    val url: String
)
data class ListPokemonApiResult(
    val count: Int,
    val next: String?,
    val previous: String?, //? significa que a propriedade pode aceitar Null
    val results: List<ListPokemonResult>
)

interface PokeApiService {

    // https://pokeapi.co/api/v2/pokemon?limit=20&offset=0
    // Base: https://pokeapi.co/api/v2/
    // Endpoint (Rota): pokemon?limit=20&offset=0
    @GET("pokemon?limit=20&offset=0")
    fun listPokemon(): Call<ListPokemonApiResult>
}
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
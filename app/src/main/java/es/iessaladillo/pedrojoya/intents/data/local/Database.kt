package es.iessaladillo.pedrojoya.intents.data.local

import android.graphics.drawable.Drawable
import es.iessaladillo.pedrojoya.intents.R
import es.iessaladillo.pedrojoya.intents.data.local.model.Pokemon

// TODO: Haz que Database implemente DataSource
object Database : DataSource {
    private var pokemons: List<Pokemon> = listOf(
        Pokemon(0, "Bulbasur", Pokemon.Tipos.PLANTA, R.drawable.bulbasur),
        Pokemon(1, "Giratina", Pokemon.Tipos.DRAGON, R.drawable.giratina),
        Pokemon(2, "Cubone", Pokemon.Tipos.NORMAL, R.drawable.cubone),
        Pokemon(3, "Gyarados", Pokemon.Tipos.AGUA, R.drawable.gyarados),
        Pokemon(4, "Freebas", Pokemon.Tipos.AGUA, R.drawable.feebas),
        Pokemon(5, "Pikachu", Pokemon.Tipos.RAYO, R.drawable.pikachu),
    )

    override fun getRandomPokemon(): Pokemon = pokemons.random()

    override fun getAllPokemons(): List<Pokemon> = pokemons.toList()

    override fun getPokemonById(id: Long): Pokemon? = pokemons.find { pokemon -> pokemon.id == id }



}
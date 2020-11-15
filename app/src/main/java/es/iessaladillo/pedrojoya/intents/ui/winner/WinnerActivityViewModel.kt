package es.iessaladillo.pedrojoya.intents.ui.winner

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.intents.data.local.Database
import es.iessaladillo.pedrojoya.intents.data.local.model.Pokemon

class WinnerActivityViewModel : ViewModel() {
    private val _pokemon: MutableLiveData<Pokemon> = MutableLiveData(Database.getRandomPokemon())
    val pokemon: LiveData<Pokemon>
        get() = _pokemon

    fun getWinner(id: Long) {
        _pokemon.value = Database.getPokemonById(id)
    }
}
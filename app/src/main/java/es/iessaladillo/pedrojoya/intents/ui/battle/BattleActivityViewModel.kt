package es.iessaladillo.pedrojoya.intents.ui.battle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.intents.data.local.Database
import es.iessaladillo.pedrojoya.intents.data.local.model.Pokemon

private const val STATE_POKEMON1: String = "STATE_POKEMON1"
private const val STATE_POKEMON2: String = "STATE_POKEMON2"

class BattleActivityViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    private val _pokemon1: MutableLiveData<Pokemon> =
        savedStateHandle.getLiveData(STATE_POKEMON1, Database.getRandomPokemon())
    val pokemon1: LiveData<Pokemon>
        get() = _pokemon1
    private val _pokemon2: MutableLiveData<Pokemon> =
        savedStateHandle.getLiveData(STATE_POKEMON2, Database.getRandomPokemon())
    val pokemon2: LiveData<Pokemon>
        get() = _pokemon2

    fun changePokemon1(index: Long) {
        _pokemon1.value = Database.getPokemonById(index)
    }

    fun changePokemon2(index: Long) {
        _pokemon2.value = Database.getPokemonById(index)
    }

    fun ganadorPelea(): Pokemon {
        return if (pokemon1.value!!.tipo.ganador(pokemon1.value!!.tipo,
                pokemon2.value!!.tipo) == 1
        ) {
            Database.getPokemonById(pokemon1.value!!.id)!!
        } else {
            Database.getPokemonById(pokemon2.value!!.id)!!
        }
    }
}
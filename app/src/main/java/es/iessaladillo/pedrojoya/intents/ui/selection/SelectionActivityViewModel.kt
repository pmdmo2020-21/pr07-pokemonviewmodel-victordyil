package es.iessaladillo.pedrojoya.intents.ui.selection

import android.view.View
import android.widget.ImageButton
import android.widget.RadioButton
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SelectionActivityViewModel : ViewModel() {

    private val _pokemonSeleccionado: MutableLiveData<Int> = MutableLiveData(0)

    val pokemonSeleccionado: LiveData<Int>
        get() = _pokemonSeleccionado

    fun elegirConBoton(v: View, botones: Array<RadioButton>) {
        for ((value, radioButton) in botones.withIndex()) {
            if (radioButton.id != v.id) {
                radioButton.isChecked = false
            } else {
                _pokemonSeleccionado.value = value
            }
        }
    }

    fun elegirConImagen(v: View, imagenes: Array<ImageButton>, botones: Array<RadioButton>) {
        for ((value0, imageButton) in imagenes.withIndex()) {
            if (imageButton.id == v.id) {
                for ((value1, radioButton) in botones.withIndex()) {
                    radioButton.isChecked = value0 == value1
                    if (value0 == value1) {
                        _pokemonSeleccionado.value = value1
                    }
                }
            }
        }
    }

}
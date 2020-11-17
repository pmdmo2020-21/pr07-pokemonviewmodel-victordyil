package es.iessaladillo.pedrojoya.intents.data.local.model

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Parcel
import android.os.Parcelable
import androidx.core.content.ContextCompat
import kotlin.random.Random

data class Pokemon(
    var id: Long, var nombre: String, var tipo: Tipos, var fotoId: Int,
) : Parcelable {

    enum class Tipos {
        AGUA,
        FUEGO,
        ROCA,
        PLANTA,
        HIELO,
        DRAGON,
        NORMAL,
        RAYO;

        fun ganador(tipoAtaque: Tipos, tipoDefensa: Tipos): Int {
            val rnd = Random(System.currentTimeMillis() / 1000)

            // 1 gana el atacante 2 el defensor
            return when (tipoAtaque) {
                AGUA -> {
                    when (tipoDefensa) {
                        AGUA -> rnd.nextInt(2) + 1
                        FUEGO -> 1
                        ROCA -> 1
                        PLANTA -> 2
                        HIELO -> rnd.nextInt(2) + 1
                        DRAGON -> rnd.nextInt(2) + 1
                        NORMAL -> rnd.nextInt(2) + 1
                        RAYO -> 2
                    }
                }
                FUEGO -> {
                    when (tipoDefensa) {
                        AGUA -> 2
                        FUEGO -> rnd.nextInt(2) + 1
                        ROCA -> 1
                        PLANTA -> 1
                        HIELO -> 1
                        DRAGON -> 2
                        NORMAL -> rnd.nextInt(2) + 1
                        RAYO -> 1
                    }
                }
                ROCA -> {
                    when (tipoDefensa) {
                        AGUA -> 2
                        FUEGO -> rnd.nextInt(2) + 1
                        ROCA -> 1
                        PLANTA -> 1
                        HIELO -> 1
                        DRAGON -> 2
                        NORMAL -> rnd.nextInt(2) + 1
                        RAYO -> rnd.nextInt(2) + 1
                    }
                }
                PLANTA -> {
                    when (tipoDefensa) {
                        AGUA -> 2
                        FUEGO -> rnd.nextInt(2) + 1
                        ROCA -> 1
                        PLANTA -> 1
                        HIELO -> 1
                        DRAGON -> 2
                        NORMAL -> rnd.nextInt(2) + 1
                        RAYO -> rnd.nextInt(2) + 1
                    }
                }
                HIELO -> {
                    when (tipoDefensa) {
                        AGUA -> 2
                        FUEGO -> rnd.nextInt(2) + 1
                        ROCA -> 1
                        PLANTA -> 1
                        HIELO -> 1
                        DRAGON -> 2
                        NORMAL -> rnd.nextInt(2) + 1
                        RAYO -> 2
                    }
                }
                DRAGON -> {
                    when (tipoDefensa) {
                        AGUA -> 2
                        FUEGO -> rnd.nextInt(2) + 1
                        ROCA -> 1
                        PLANTA -> 1
                        HIELO -> 1
                        DRAGON -> 2
                        NORMAL -> rnd.nextInt(2) + 1
                        RAYO -> rnd.nextInt(2) + 1

                    }
                }
                NORMAL -> {
                    when (tipoDefensa) {
                        AGUA -> 2
                        FUEGO -> rnd.nextInt(2) + 1
                        ROCA -> 1
                        PLANTA -> 1
                        HIELO -> 1
                        DRAGON -> 2
                        NORMAL -> rnd.nextInt(2) + 1
                        RAYO -> rnd.nextInt(2) + 1
                    }
                }
                RAYO -> {
                    when (tipoDefensa) {
                        AGUA -> 1
                        FUEGO -> rnd.nextInt(2) + 1
                        ROCA -> 1
                        PLANTA -> 1
                        HIELO -> 1
                        DRAGON -> rnd.nextInt(2) + 1
                        NORMAL -> rnd.nextInt(2) + 1
                        RAYO -> rnd.nextInt(2) + 1
                    }
                }

            }
        }
    }

    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString()!!,
        Tipos.values()[parcel.readInt()],
        parcel.readInt()) {
    }

    fun getDrawable(context: Context): Drawable {
        return ContextCompat.getDrawable(context, fotoId)!!
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(nombre)
        parcel.writeInt(tipo.ordinal)
        parcel.writeInt(fotoId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Pokemon> {
        override fun createFromParcel(parcel: Parcel): Pokemon {
            return Pokemon(parcel)
        }

        override fun newArray(size: Int): Array<Pokemon?> {
            return arrayOfNulls(size)
        }
    }
}
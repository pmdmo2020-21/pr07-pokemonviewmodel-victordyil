package es.iessaladillo.pedrojoya.intents.ui.selection

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.RadioButton
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.intents.databinding.SelectionActivityBinding

const val EXTRA_ID = "EXTRA_ID"

class SelectionActivity : AppCompatActivity() {
    private val viewModel: SelectionActivityViewModel by viewModels()

    private val pokImg: Array<ImageButton> by lazy {
        arrayOf(
            binding.imageButton0,
            binding.imageButton1,
            binding.imageButton2,
            binding.imageButton3,
            binding.imageButton4,
            binding.imageButton5,
        )
    }
    private val pokBtn: Array<RadioButton> by lazy {
        arrayOf(
            binding.rb0,
            binding.rb1,
            binding.rb2,
            binding.rb3,
            binding.rb4,
            binding.rb5
        )
    }

    private val binding: SelectionActivityBinding by lazy {
        SelectionActivityBinding.inflate(layoutInflater)
    }

    companion object {
        fun newIntent(context: Context, id: Long): Intent {
            return Intent(context, SelectionActivity::class.java)
                .putExtra(EXTRA_ID, id)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.constraintLayout)
        setupViews()
    }

    private fun setupViews() {

        for (imageButton in pokImg) {
            imageButton.setOnClickListener { v -> viewModel.elegirConImagen(v, pokImg, pokBtn) }
        }
        for (radioButton in pokBtn) {
            radioButton.setOnClickListener { v -> viewModel.elegirConBoton(v, pokBtn) }
        }
    }

    override fun onBackPressed() {
        var r = Intent().putExtra(EXTRA_ID, viewModel.pokemonSeleccionado.value?.toLong());
        setResult(RESULT_OK, r)
        super.onBackPressed()


    }


}
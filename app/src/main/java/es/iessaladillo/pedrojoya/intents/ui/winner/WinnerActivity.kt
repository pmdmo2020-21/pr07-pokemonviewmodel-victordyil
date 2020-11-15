package es.iessaladillo.pedrojoya.intents.ui.winner

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.intents.data.local.Database
import es.iessaladillo.pedrojoya.intents.data.local.model.Pokemon
import es.iessaladillo.pedrojoya.intents.databinding.BattleActivityBinding
import es.iessaladillo.pedrojoya.intents.databinding.SelectionActivityBinding
import es.iessaladillo.pedrojoya.intents.databinding.WinnerActivityBinding

const val EXTRA_LONG = "GANADOR"

class WinnerActivity : AppCompatActivity() {

    private val binding: WinnerActivityBinding by lazy {
        WinnerActivityBinding.inflate(layoutInflater)
    }
    private val viewModel: WinnerActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.constraintLayout)
        setupViews()

    }

    private fun setupViews() {
        viewModel.getWinner(intent.getLongExtra(EXTRA_LONG, 0))
        binding.imageView.setImageDrawable(viewModel.pokemon.value?.getDrawable(this))
        binding.ganadorTxt.text = viewModel.pokemon.value?.nombre
    }

}
package es.iessaladillo.pedrojoya.intents.ui.battle

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import es.iessaladillo.pedrojoya.intents.data.local.Database
import es.iessaladillo.pedrojoya.intents.data.local.model.Pokemon
import es.iessaladillo.pedrojoya.intents.databinding.BattleActivityBinding
import es.iessaladillo.pedrojoya.intents.ui.selection.EXTRA_ID
import es.iessaladillo.pedrojoya.intents.ui.selection.SelectionActivity
import es.iessaladillo.pedrojoya.intents.ui.winner.EXTRA_LONG
import es.iessaladillo.pedrojoya.intents.ui.winner.WinnerActivity


class BattleActivity : AppCompatActivity() {

    private val binding: BattleActivityBinding by lazy {
        BattleActivityBinding.inflate(layoutInflater)
    }

    private val viewModel: BattleActivityViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(binding.constraintLayout)
        setupViews()
        observerPokemons()
    }

    private fun observerPokemons() {
        viewModel.pokemon1.observe(this, { pokemon -> reloadPokemon1(pokemon) })
        viewModel.pokemon2.observe(this, { pokemon -> reloadPokemon2(pokemon) })
    }

    private fun setupViews() {
        reloadPokemon1(viewModel.pokemon1.value!!)
        reloadPokemon2(viewModel.pokemon2.value!!)

        binding.pok1.setOnClickListener {
            selectCall1.launch(SelectionActivity.newIntent(this, viewModel.pokemon1.value!!.id))
        }

        binding.pok2.setOnClickListener {
            selectCall2.launch(SelectionActivity.newIntent(this, viewModel.pokemon2.value!!.id))
        }

        binding.fightBtn.setOnClickListener { pelear() }
    }


    private fun pelear() {
        val intent = Intent(this, WinnerActivity::class.java).putExtra(EXTRA_LONG,
            viewModel.ganadorPelea().id)
        startActivity(intent)
    }


    //Prepare to livedata
    private fun reloadPokemon1(pokemon: Pokemon) {
        binding.pk1lbl.text = pokemon.nombre
        binding.pok1.setImageDrawable(pokemon.getDrawable(this))
    }

    private fun reloadPokemon2(pokemon: Pokemon) {
        binding.pk2lbl.text = pokemon.nombre
        binding.pok2.setImageDrawable(pokemon.getDrawable(this))
    }


    //Llamadas a otro intent
    private val selectCall1: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultIntent = result.data
            if (result.resultCode == Activity.RESULT_OK && resultIntent != null) {
                viewModel.changePokemon1(resultIntent.getLongExtra(EXTRA_ID, 0))
            }
        }
    private val selectCall2: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultIntent = result.data
            if (result.resultCode == Activity.RESULT_OK && resultIntent != null) {
                viewModel.changePokemon2(resultIntent.getLongExtra(EXTRA_ID, 0))
            }
        }


}
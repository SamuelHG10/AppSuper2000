package com.esaudev.graphqlapollo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.viewModels
import com.esaudev.graphqlapollo.adapter.CharactersListAdapter
import com.esaudev.graphqlapollo.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: CharacterViewModel by viewModels()
    private val charactersListAdapter = CharactersListAdapter()
    private lateinit var btnIrCarrito : Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()

        binding.charactersList.apply { adapter = charactersListAdapter }
        observeData()
        viewModel.queryCharactersList()

        btnIrCarrito = findViewById(R.id.btrnIrCarro)

        btnIrCarrito.setOnClickListener {
            val intent = Intent(this, Carrito::class.java)
            startActivity(intent)
        }

    }

    private fun observeData() {
        viewModel.charactersList.observe(this) { response ->
            when (response) {
                is ViewState.Success -> {
                    if (response.value?.data?.listaProductos?.size == 0) {
                        charactersListAdapter.submitList(emptyList())
                        return@observe
                    }
                    val results = response.value?.data?.listaProductos
                    charactersListAdapter.submitList(results)
                }
                else -> Unit
            }
        }
    }

}
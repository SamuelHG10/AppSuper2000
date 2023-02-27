package com.esaudev.graphqlapollo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class CompraConfirmada : AppCompatActivity() {

    lateinit var btnGoMenu : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compra_confirmada)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()

        btnGoMenu = findViewById(R.id.btnIrMenu)
        btnGoMenu.setOnClickListener {
            val intent = Intent(this, PrimeraVista::class.java)
            startActivity(intent)
        }

    }
}
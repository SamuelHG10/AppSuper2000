package com.esaudev.graphqlapollo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.esaudev.graphqlapollo.adapter.CharactersListAdapter.Companion.subTotal

class Carrito : AppCompatActivity() {

    lateinit var btnConfirmo : Button
    lateinit var txtNombre : EditText
    lateinit var txtNumeroTarjeta : EditText
    lateinit var txtExpiracion : EditText
    lateinit var txtCCV : EditText

    private lateinit var txtTotal : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrito)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()

        txtNombre = findViewById(R.id.txtNombre)
        txtNumeroTarjeta = findViewById(R.id.txtNumeroTarjeta)
        txtExpiracion = findViewById(R.id.txtFechaExpiracion)
        txtCCV = findViewById(R.id.txtCCV)

        btnConfirmo = findViewById(R.id.btnConfirmar)

        txtTotal = findViewById(R.id.txtTotal)

        val numeros = floatArrayOf(subTotal.toFloat())
        var sumaTotal1 = 0f
        for (numero in numeros){
            sumaTotal1 += numero
        }
        txtTotal.setText(sumaTotal1.toString())


        btnConfirmo.setOnClickListener {
            if(txtNombre.text.toString().isNotEmpty() && txtNumeroTarjeta.text.toString().isNotEmpty() &&
                txtExpiracion.text.toString().isNotEmpty() && txtCCV.text.toString().isNotEmpty()){
                val intent = Intent(this, CompraConfirmada::class.java)
                startActivity(intent)
                Toast.makeText(this, "Compra aprovada", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, "Los campos estan vacios", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
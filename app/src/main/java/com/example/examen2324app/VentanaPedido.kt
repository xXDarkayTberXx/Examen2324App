package com.example.examen2324app

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.examen2324app.MainActivity.Companion.HORA_PEDIDO
import com.example.examen2324app.MainActivity.Companion.NOM_PEDIDO
import com.example.examen2324app.MainActivity.Companion.NUM_ASIENTOS

class VentanaPedido : AppCompatActivity() {

    private lateinit var tvVeredicto: TextView

    private var nAsientos: String = ""
    private var nombre: String = ""
    private var hora: String = ""

    private fun recibirExtras() {
        recibirHora()
        recibirNombre()
        recibirNumAsientos()
    }

    private fun initComponents() {
        tvVeredicto = findViewById(R.id.tvVeredicto)
    }

    private fun recibirHora() {
        hora = intent.extras?.getString(HORA_PEDIDO).orEmpty()
    }

    private fun recibirNombre() {
        nombre = intent.extras?.getString(NOM_PEDIDO).orEmpty()
    }

    private fun recibirNumAsientos() {
        nAsientos = intent.extras?.getString(NUM_ASIENTOS).orEmpty()
    }

    @SuppressLint("SetTextI18n")
    private fun formularVeredicto() {
        tvVeredicto.text =
            getString(R.string.mesareservada1) + nAsientos + getString(R.string.mesareservada2) + nombre + getString(
                R.string.mesareservada3
            ) + hora
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        supportActionBar?.hide()
        setContentView(R.layout.activity_ventana_pedido)
        recibirExtras()
        initComponents()
        formularVeredicto()
    }


}
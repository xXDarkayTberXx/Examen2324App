package com.example.examen2324app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    companion object {
        const val HORA_PEDIDO = "HORA_PEDIDO"
        const val NOM_PEDIDO = "NOM_PEDIDO"
        const val NUM_ASIENTOS = "NUM_ASIENTOS"
    }

    private lateinit var cv1: CardView
    private lateinit var cv2: CardView
    private lateinit var cv3: CardView
    private lateinit var tvNombreRestaurante: TextView
    private lateinit var tvIntroducirNombre: TextView
    private lateinit var tvHoras: TextView
    private lateinit var textInputNombre: TextInputEditText
    private lateinit var btnAnteriorHora: AppCompatImageView
    private lateinit var btnSiguienteHora: AppCompatImageView
    private lateinit var btnValidar: AppCompatImageView
    private lateinit var cvhourSelection: CardView

    var mesaPedida: String = ""

    private fun initListeners() {
        cv1.setOnClickListener {
            changeCardviewColors(cv1)
            mostrarTodo()
            mesaPedida = R.id.cv1.toString()
        }
        cv2.setOnClickListener {
            changeCardviewColors(cv2)
            mostrarTodo()
            mesaPedida = R.id.cv2.toString()
        }
        cv3.setOnClickListener {
            changeCardviewColors(cv3)
            mostrarTodo()
            mesaPedida = R.id.cv3.toString()
        }
        btnAnteriorHora.setOnClickListener {
            reduceHour()
        }
        btnSiguienteHora.setOnClickListener {
            addHour()
        }
        btnValidar.setOnClickListener {
            validarPedido()
        }
    }

    private fun validarPedido() {
        val intentPED = Intent(this, VentanaPedido::class.java)
        intentPED.putExtra("HORA_PEDIDO", " " + tvHoras.text.toString() + " ")
        validarNombreNoVacio(intentPED)
        mandarNumAsientos(intentPED)
        if (validarNombreNoVacio(intentPED)){
            startActivity(intentPED)
        }
    }

    private fun validarNombreNoVacio(intentPED: Intent):Boolean {
        if (textInputNombre.text.toString().isEmpty()) {
            Toast.makeText(this, "El nombre no puede estar vacío", Toast.LENGTH_LONG).show()
            return false
        } else {
            intentPED.putExtra("NOM_PEDIDO", " " + textInputNombre.text.toString() + " ")
            return true
        }
    }
    private fun mandarNumAsientos(intentPED: Intent) {
        if (mesaPedida == "cv1") {
            intentPED.putExtra("NUM_ASIENTOS", " 2 ")
        } else if (mesaPedida == "cv2") {
            intentPED.putExtra("NUM_ASIENTOS", " 4 ")
        } else {
            intentPED.putExtra("NUM_ASIENTOS", " 8 ")
        }
    }

    private fun initComponents() {
        cv1 = findViewById(R.id.cv1)
        cv2 = findViewById(R.id.cv2)
        cv3 = findViewById(R.id.cv3)
        cvhourSelection = findViewById(R.id.cvhourSelection)
        tvNombreRestaurante = findViewById(R.id.tvNombreRestaurante)
        tvIntroducirNombre = findViewById(R.id.tvIntroducirNombre)
        tvHoras = findViewById(R.id.tvHoras)
        textInputNombre = findViewById(R.id.textInputNombre)
        btnAnteriorHora = findViewById(R.id.btnAnteriorHora)
        btnSiguienteHora = findViewById(R.id.btnSiguienteHora)
        btnValidar = findViewById(R.id.btnValidar)
    }

    private fun mostrarTodo() {
        cvhourSelection.visibility = View.VISIBLE
        tvIntroducirNombre.visibility = View.VISIBLE
        tvHoras.visibility = View.VISIBLE
        btnAnteriorHora.visibility = View.VISIBLE
        btnSiguienteHora.visibility = View.VISIBLE
        textInputNombre.visibility = View.VISIBLE
        btnValidar.visibility = View.VISIBLE
    }

    private fun ocultarTodo() {
        cvhourSelection.visibility = View.INVISIBLE
        tvIntroducirNombre.visibility = View.INVISIBLE
        tvHoras.visibility = View.INVISIBLE
        btnAnteriorHora.visibility = View.INVISIBLE
        btnSiguienteHora.visibility = View.INVISIBLE
        textInputNombre.visibility = View.INVISIBLE
        btnValidar.visibility = View.INVISIBLE
    }

    private fun reduceHour() {
        val horaocho: String = getString(R.string.horaocho)
        val horanueve: String = getString(R.string.horanueve)
        val horadiez: String = getString(R.string.horadiez)
        when (tvHoras.text.toString()) {
            horadiez -> tvHoras.text = horanueve
            horanueve -> tvHoras.text = horaocho
            horaocho -> tvHoras.text = horadiez
        }
    }

    private fun addHour() {
        val horaocho: String = getString(R.string.horaocho)
        val horanueve: String = getString(R.string.horanueve)
        val horadiez: String = getString(R.string.horadiez)

        when (tvHoras.text.toString()) {
            horaocho -> tvHoras.text = horanueve
            horanueve -> tvHoras.text = horadiez
            horadiez -> tvHoras.text = horaocho
        }

    }

    private fun initUI() {
        ocultarTodo()
    }

    private fun changeCardviewColors(cvSelected: CardView) {
        when (cvSelected.id){
            R.id.cv1 -> {cv1.setCardBackgroundColor(ContextCompat.getColor(this, R.color.cc_bg_elemento_sel))
                cv2.setCardBackgroundColor(ContextCompat.getColor(this, R.color.cc_bg_elemento))
                cv3.setCardBackgroundColor(ContextCompat.getColor(this, R.color.cc_bg_elemento))}
            R.id.cv2 -> {cv2.setCardBackgroundColor(ContextCompat.getColor(this, R.color.cc_bg_elemento_sel))
                cv1.setCardBackgroundColor(ContextCompat.getColor(this, R.color.cc_bg_elemento))
                cv3.setCardBackgroundColor(ContextCompat.getColor(this, R.color.cc_bg_elemento))}
            R.id.cv3 -> {cv3.setCardBackgroundColor(ContextCompat.getColor(this, R.color.cc_bg_elemento_sel))
                cv1.setCardBackgroundColor(ContextCompat.getColor(this, R.color.cc_bg_elemento))
                cv2.setCardBackgroundColor(ContextCompat.getColor(this, R.color.cc_bg_elemento))}
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)
        initComponents()
        initListeners()
        initUI()
    }


}
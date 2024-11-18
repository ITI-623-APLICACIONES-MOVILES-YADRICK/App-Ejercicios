package com.example.appgym

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Pantalla4 : AppCompatActivity() {
    private var isRunning = false
    private var timeInMilliseconds = 0
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable

    private lateinit var textCronometro: TextView
    private lateinit var buttonIniciar: Button
    private lateinit var buttonPausa: Button
    private lateinit var buttonReiniciar: Button
    private lateinit var imgMuestra: ImageView
    private lateinit var buttonAtras: Button
    private lateinit var buttonAlerta: Button // Añadir referencia para btnAlerta

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla4)

        // Inicializa las vistas
        textCronometro = findViewById(R.id.textCronometro)
        buttonIniciar = findViewById(R.id.btnIniciar)
        buttonPausa = findViewById(R.id.btnPausa)
        buttonReiniciar = findViewById(R.id.btnReiniciar)
        imgMuestra = findViewById(R.id.imgMuestra)
        buttonAtras = findViewById(R.id.btnAtras)
        buttonAlerta = findViewById(R.id.btnAlerta) // Inicializa el botón btnAlerta

        // Obtener el ID de la imagen pasada desde Pantalla3
        val selectedImageResId = intent.getIntExtra("selectedImage", -1)
        if (selectedImageResId != -1) {
            imgMuestra.setImageResource(selectedImageResId)
        }

        handler = Handler()

        // Runnable que actualiza el cronómetro
        runnable = object : Runnable {
            override fun run() {
                if (isRunning) {
                    timeInMilliseconds += 100
                    updateCronometro()
                    handler.postDelayed(this, 100)
                }
            }
        }

        buttonIniciar.setOnClickListener {
            isRunning = true
            handler.post(runnable)
        }

        buttonPausa.setOnClickListener {
            isRunning = false
        }

        buttonReiniciar.setOnClickListener {
            isRunning = false
            timeInMilliseconds = 0
            updateCronometro()
        }

        // Configuración del botón "Atras"
        buttonAtras.setOnClickListener {
            finish()
        }

        // Configuración del botón "Alerta"
        buttonAlerta.setOnClickListener {
            val intent = Intent(this, Pantalla6::class.java)
            startActivity(intent) // Inicia Pantalla6
        }
    }

    private fun updateCronometro() {
        val minutes = (timeInMilliseconds / 1000) / 60
        val seconds = (timeInMilliseconds / 1000) % 60
        val milliseconds = (timeInMilliseconds % 1000) / 10
        textCronometro.text = String.format("%02d:%02d:%02d", minutes, seconds, milliseconds)
    }
}

package com.example.appgym

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Pantalla5 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla5)

        // Obtener la ID del recurso de imagen seleccionada
        val selectedImageResId = intent.getIntExtra("selectedImage", -1)

        // Cargar la imagen en imgPrevia
        if (selectedImageResId != -1) {
            val imageView: ImageView = findViewById(R.id.imgPrevia)
            imageView.setImageResource(selectedImageResId) // Mostrar la imagen seleccionada
        }

        // Configuración del texto
        val textView: TextView = findViewById(R.id.textProgreso)
        textView.text = "TU PROGRESO" // Puedes personalizar el texto según sea necesario

        // Configuración del botón Regresar
        val btnRegresa: Button = findViewById(R.id.btnRegresa)
        btnRegresa.setOnClickListener {
            finish() // Cierra la actividad actual y regresa a la anterior
        }
    }
}

package com.example.appgym

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Pantalla3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pantalla3)

        // Obtener la ID del recurso de imagen seleccionada
        val selectedImageResId = intent.getIntExtra("selectedImage", -1)

        // Cargar la imagen en imgEmpezar
        if (selectedImageResId != -1) {
            val imageView: ImageView = findViewById(R.id.imgEmpezar)
            imageView.setImageResource(selectedImageResId) // Mostrar la imagen seleccionada
        }

        // Configuración del botón "Empezar"
        val buttonEmpezar: Button = findViewById(R.id.buttonEmpezar)
        buttonEmpezar.setOnClickListener {
            val intent = Intent(this, Pantalla4::class.java)
            intent.putExtra("selectedImage", selectedImageResId) // Pasar el ID de la imagen seleccionada a Pantalla4
            startActivity(intent)
        }

        // Configuración del botón "Ver Mi Progreso"
        val buttonProgreso: Button = findViewById(R.id.buttonProgreso)
        buttonProgreso.setOnClickListener {
            val intent = Intent(this, Pantalla5::class.java)
            intent.putExtra("selectedImage", selectedImageResId) // Pasar el ID de la imagen seleccionada a Pantalla5
            startActivity(intent)
        }

        // Configuración del botón "Atras"
        val buttonAtras: Button = findViewById(R.id.botonAtras)
        buttonAtras.setOnClickListener {
            finish() // Regresar a la actividad anterior (Pantalla2)
        }

        // Ajuste de padding para las barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.imgEmpezar)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}

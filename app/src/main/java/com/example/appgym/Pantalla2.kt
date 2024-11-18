package com.example.appgym

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Pantalla2 : AppCompatActivity() {
    private lateinit var handler: Handler
    private var selectedImageResId: Int? = null // Para guardar el ID del recurso de imagen seleccionada

    private val imageButtonMap = mapOf(
        R.id.btn_img1 to R.mipmap.img1, // Reemplaza con tus recursos
        R.id.btn_img2 to R.mipmap.img2,
        R.id.btn_img3 to R.mipmap.img3,
        R.id.btn_img4 to R.mipmap.img4,
        R.id.btn_img5 to R.mipmap.img5,
        R.id.btn_img6 to R.mipmap.img6,
        R.id.btn_img7 to R.mipmap.img7,
        R.id.btn_img8 to R.mipmap.img8
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pantalla2)

        // Inicializar el Handler
        handler = Handler(mainLooper)

        // Configuración de los botones de imagen
        imageButtonMap.keys.forEach { buttonId ->
            setupButton(buttonId)
        }

        // Configuración del botón Cancelar
        val botonCancelar: Button = findViewById(R.id.botonCancelar)
        botonCancelar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Opcional: finaliza la actividad actual
        }

        // Configuración del botón Ayuda
        val btnAyuda: Button = findViewById(R.id.btn_ayuda)
        btnAyuda.setOnClickListener {
            showHelpDialog()
        }
    }

    private fun setupButton(buttonId: Int) {
        val button: ImageButton = findViewById(buttonId)

        // Configurar el OnTouchListener
        button.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // Mostrar mensaje o realizar alguna acción si es necesario
                    true
                }
                MotionEvent.ACTION_UP -> {
                    selectedImageResId = imageButtonMap[buttonId] // Guardar el ID del recurso de imagen seleccionada
                    val intent = Intent(this, Pantalla3::class.java)
                    intent.putExtra("selectedImage", selectedImageResId) // Pasar el recurso de imagen a Pantalla3
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(button) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun showHelpDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Ayuda")
            .setMessage("PRESIONA UNA IMAGEN PARA COMENZAR EL EJERCICIO")
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
    }
}

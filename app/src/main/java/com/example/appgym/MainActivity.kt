package com.example.appgym

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var nombreEditText: EditText
    private lateinit var apellidosEditText: EditText
    private lateinit var edadEditText: EditText
    private lateinit var generoEditText: EditText
    private lateinit var nacionalidadEditText: EditText
    private lateinit var pesoEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializa los EditText
        nombreEditText = findViewById(R.id.textNombre)
        apellidosEditText = findViewById(R.id.textApellidos)
        edadEditText = findViewById(R.id.textEdad)
        generoEditText = findViewById(R.id.textGenero)
        nacionalidadEditText = findViewById(R.id.textNacionalidad)
        pesoEditText = findViewById(R.id.textPeso)

        // Añade TextWatcher para validar en tiempo real
        edadEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!isNumeric(s.toString())) {
                    edadEditText.error = "Formato inválido en Edad"
                }
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        pesoEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!isNumeric(s.toString())) {
                    pesoEditText.error = "Formato inválido en Peso"
                }
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        val VentanaPantalla2: Button = findViewById(R.id.BtnSiguiente)
        VentanaPantalla2.setOnClickListener {
            if (validateFields()) {
                openActivity(Pantalla2::class.java)
            }
        }
    }

    private fun validateFields(): Boolean {
        // Verifica que los campos no estén vacíos
        if (nombreEditText.text.isEmpty() ||
            apellidosEditText.text.isEmpty() ||
            generoEditText.text.isEmpty() ||
            nacionalidadEditText.text.isEmpty() ||
            edadEditText.text.isEmpty() ||
            pesoEditText.text.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos correctamente", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun isNumeric(str: String): Boolean {
        return str.all { it.isDigit() }
    }

    private fun openActivity(activity: Class<*>) {
        val intent = Intent(this, activity)
        startActivity(intent)
    }
}

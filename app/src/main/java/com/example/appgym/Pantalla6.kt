package com.example.appgym

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity

class Pantalla6 : AppCompatActivity() {
    private lateinit var datePicker: DatePicker
    private lateinit var timePicker: TimePicker
    private lateinit var btnCrearAlerta: Button
    private lateinit var textCrearAlerta: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla6)

        datePicker = findViewById(R.id.datePicker)
        timePicker = findViewById(R.id.timePicker)
        btnCrearAlerta = findViewById(R.id.btnCrearAlerta)
        textCrearAlerta = findViewById(R.id.textCrearAlerta)

        // Listener para el DatePicker
        datePicker.setOnDateChangedListener { _, _, _, _ ->
            // Mostrar el TimePicker cuando se elige una fecha
            timePicker.visibility = View.VISIBLE
        }

        btnCrearAlerta.setOnClickListener {
            val day = datePicker.dayOfMonth
            val month = datePicker.month + 1 // Los meses son 0-indexados
            val year = datePicker.year
            var hour = timePicker.hour
            val minute = timePicker.minute

            // Determinar AM o PM
            val amPm = if (hour < 12) "AM" else "PM"

            // Convertir a formato 12 horas
            if (hour > 12) {
                hour -= 12
            } else if (hour == 0) {
                hour = 12 // Ajustar para la medianoche
            }

            // Mostrar la fecha y hora seleccionadas con saltos de línea y AM/PM
            textCrearAlerta.text = "ALERTA CREDA PARA:\n$day/$month/$year\n\tA LAS\n $hour:${String.format("%02d", minute)} $amPm"

        }
    }
}

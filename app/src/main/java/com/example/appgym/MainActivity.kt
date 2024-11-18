package com.example.appgym

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import java.io.File
import java.io.IOException
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings

class MainActivity : AppCompatActivity() {

    private lateinit var nombreEditText: EditText
    private lateinit var apellidosEditText: EditText
    private lateinit var edadEditText: EditText
    private lateinit var generoEditText: EditText
    private lateinit var nacionalidadEditText: EditText
    private lateinit var pesoEditText: EditText
    private lateinit var imageView: ImageView
    private lateinit var imageButton: ImageButton

    private var currentPhotoPath: String? = null

    // Lanzadores de actividad para la cámara y galería
    private lateinit var cameraLauncher: ActivityResultLauncher<Intent>
    private lateinit var galleryLauncher: ActivityResultLauncher<Intent>

    // Definir el código de solicitud de permisos
    private val PERMISSION_REQUEST_CODE = 100

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
        imageView = findViewById(R.id.imageView)
        imageButton = findViewById(R.id.btn_Photo)

        // Verifica los permisos de cámara y almacenamiento al inicio
        checkPermissions()

        // Configura el launcher para la cámara
        cameraLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                currentPhotoPath?.let {
                    val imageUri = Uri.parse(it)
                    imageView.setImageURI(imageUri)
                }
            } else {
                Toast.makeText(this, "No se capturó la imagen", Toast.LENGTH_SHORT).show()
            }
        }

        // Configura el launcher para la galería
        galleryLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val selectedImageUri = result.data?.data
                try {
                    selectedImageUri?.let {
                        imageView.setImageURI(it)
                    }
                } catch (e: IOException) {
                    Toast.makeText(this, "Error al seleccionar la imagen", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Error al seleccionar la imagen", Toast.LENGTH_SHORT).show()
            }
        }

        imageButton.setOnClickListener {
            openCameraOrGallery()
        }

        val VentanaPantalla2: Button = findViewById(R.id.BtnSiguiente)
        VentanaPantalla2.setOnClickListener {
            if (validateFields()) {
                openActivity(Pantalla2::class.java)
            }
        }
    }

    private fun openCameraOrGallery() {
        val options = arrayOf("Cámara", "Galería")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Selecciona una opción")
        builder.setItems(options) { dialog, which ->
            when (which) {
                0 -> openCamera()
                1 -> openGallery()
            }
        }
        builder.show()
    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent.resolveActivity(packageManager) != null) {
            val imageFile: File? = try {
                createImageFile()
            } catch (e: IOException) {
                Toast.makeText(this, "Error al crear el archivo para la imagen", Toast.LENGTH_SHORT).show()
                null
            }

            imageFile?.also {
                val imageUri = FileProvider.getUriForFile(
                    this,
                    "com.example.appgym.fileprovider",
                    it
                )
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
                currentPhotoPath = imageUri.toString()
                cameraLauncher.launch(intent)
            }
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        galleryLauncher.launch(intent)
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        val imageFileName = "JPEG_${System.currentTimeMillis()}_"
        val storageDir = getExternalFilesDir(null)
        return File.createTempFile(imageFileName, ".jpg", storageDir).apply {
            currentPhotoPath = absolutePath
        }
    }

    private fun validateFields(): Boolean {
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

    private fun openActivity(activity: Class<*>) {
        val intent = Intent(this, activity)
        startActivity(intent)
    }

    private fun checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE),
                PERMISSION_REQUEST_CODE
            )
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                Toast.makeText(this, "Permisos otorgados", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Permisos denegados", Toast.LENGTH_SHORT).show()
                showPermissionDeniedMessage()
            }
        }
    }

    private fun showPermissionDeniedMessage() {
        AlertDialog.Builder(this)
            .setMessage("Se necesitan permisos para usar la cámara y almacenamiento. ¿Deseas ir a la configuración?")
            .setPositiveButton("Sí") { _, _ ->
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                val uri = Uri.fromParts("package", packageName, null)
                intent.data = uri
                startActivity(intent)
            }
            .setNegativeButton("No") { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
    }
}

package com.flknlabs.app1

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

const val CAMERA_REQUEST_CODE = 1234
class MainActivity : AppCompatActivity() {
    lateinit var btnCamera: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCamera = findViewById<Button>(R.id.btnCamera)
        btnCamera.setOnClickListener { checkCameraPermission() }
    }

    private fun checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                //El usuario ya ha rechazado el permiso anteriormente, debemos informarle que vaya a ajustes.
                Toast.makeText(this, "El usuario ya ha rechazado el permiso anteriormente", Toast.LENGTH_SHORT).show()
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), CAMERA_REQUEST_CODE)
            } else {
                //El usuario nunca ha aceptado ni rechazado, así que le pedimos que acepte el permiso.
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), CAMERA_REQUEST_CODE)
                Toast.makeText(this, "El usuario nunca ha aceptado ni rechazado", Toast.LENGTH_SHORT).show()
            }
        } else {
            //El permiso está aceptado.
            Toast.makeText(this, "El usuario ya habia aceptado el permiso", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            CAMERA_REQUEST_CODE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    //El usuario ha aceptado el permiso, no tiene porqué darle de nuevo al botón, podemos lanzar la funcionalidad desde aquí.
                    Toast.makeText(this, "El usuario acepto el permiso", Toast.LENGTH_SHORT).show()
                } else {
                    //El usuario ha rechazado el permiso, podemos desactivar la funcionalidad o mostrar una vista/diálogo.
                    Toast.makeText(this, "El usuario NOOOO acepto el permiso", Toast.LENGTH_SHORT).show()
                }
                return
            }
            else -> {
                // Este else lo dejamos por si sale un permiso que no teníamos controlado.
                Toast.makeText(this, "Ocurrió un error inesperado", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
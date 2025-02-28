package com.example.partie1

import android.content.Context
import android.content.Intent
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity

class ShakeFlashActivity : ComponentActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var accelerometer: Sensor? = null
    private var lastShakeTime: Long = 0
    private var isFlashOn = false
    private lateinit var textViewStatus: TextView
    private lateinit var imageViewGif: pl.droidsonroids.gif.GifImageView
    private lateinit var imageViewDark: pl.droidsonroids.gif.GifImageView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shake_flash)

        textViewStatus = findViewById(R.id.textViewStatus)

        imageViewGif = findViewById(R.id.imageViewDirection)
        imageViewGif.visibility = android.view.View.GONE // Caché au départ

        imageViewDark = findViewById(R.id.imgViewDark)
        imageViewDark.visibility = android.view.View.GONE // Caché au départ


        // Initialisation du capteur d'accélération
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        if (accelerometer == null) {
            textViewStatus.text = "❌ Accéléromètre non disponible"
        }

        val btnRetourAccueil = findViewById<Button>(R.id.btnRetourAccueil)
        // Bouton retour vers l'accueil
        btnRetourAccueil.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        accelerometer?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]

            val acceleration = Math.sqrt((x * x + y * y + z * z).toDouble()).toFloat()
            val currentTime = System.currentTimeMillis()

            // Détection du secouement (accélération forte et délai raisonnable)
            if (acceleration > 12 && (currentTime - lastShakeTime > 1000)) {
                lastShakeTime = currentTime
                toggleFlash()
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Ne rien faire
    }

    private fun toggleFlash() {
        val cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        try {
            val cameraId = cameraManager.cameraIdList[0] // Utilise la première caméra
            if (!isFlashOn) {
                cameraManager.setTorchMode(cameraId, true) // Allume le flash
                textViewStatus.text = "💡 Flash allumé"
                imageViewGif.visibility = android.view.View.VISIBLE // Affiche le GIF
                imageViewDark.visibility = android.view.View.GONE // Affiche le GIF

            } else {
                cameraManager.setTorchMode(cameraId, false) // Éteint le flash
                textViewStatus.text = "\uD83C\uDF11\uD83D\uDD6F\uFE0F Flash éteint"
                imageViewGif.visibility = android.view.View.GONE // Cache le GIF
                imageViewDark.visibility = android.view.View.VISIBLE // Cache le GIF

            }
            isFlashOn = !isFlashOn
        } catch (e: CameraAccessException) {
            textViewStatus.text = "❌ Erreur : Flash non disponible"
        }
    }
}

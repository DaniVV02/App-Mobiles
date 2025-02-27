package com.example.partie1

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity

class DirectionActivity : ComponentActivity(), SensorEventListener {
    private lateinit var sensorManager: SensorManager
    private var accelerometer: Sensor? = null
    private lateinit var textViewDirection: TextView
    private lateinit var imageViewDirection: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_direction)

        textViewDirection = findViewById(R.id.textViewDirection)
        imageViewDirection = findViewById(R.id.imageViewDirection)


        // Initialisation du capteur d'accélération
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        if (accelerometer == null) {
            textViewDirection.text = "❌ Accéléromètre non disponible"
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

            val (direction, imageRes) = when {
                x > 2 -> "⬅️ Gauche" to R.drawable.left_dir
                x < -2 -> "➡️ Droite" to R.drawable.right_dir
                y > 2 -> "⬆️ Haut" to R.drawable.up_dir
                y < -2 -> "⬇️ Bas" to R.drawable.down_dir
                else -> "🔄 Stable" to R.drawable.stable_dir
            }

            textViewDirection.text = "Direction : $direction"
            imageViewDirection.setImageResource(imageRes)

        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Ne rien faire pour cet exercice
    }
}
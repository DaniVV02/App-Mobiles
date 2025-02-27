package com.example.partie1

import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCapt = findViewById<Button>(R.id.btnCheckCapteurs)
        val btnSens = findViewById<Button>(R.id.btnCheckSensors)
        val btnAcc = findViewById<Button>(R.id.btnCheckAcc)
        val btnDir = findViewById<Button>(R.id.btnCheckDir)
        val btnFlash = findViewById<Button>(R.id.btnCheckFlash)
        val btnProx = findViewById<Button>(R.id.btnCheckProx)
        val btnLoc = findViewById<Button>(R.id.btnCheckLoc)


        btnCapt.setOnClickListener {
            startActivity(Intent(this, ListeCapteursActivity::class.java))
        }

        btnSens.setOnClickListener {
            startActivity(Intent(this, SensorCheckActivity::class.java))
        }

        btnAcc.setOnClickListener{
            startActivity(Intent(this, AccelerometerActivity :: class.java))
        }

        btnDir.setOnClickListener{
            startActivity(Intent(this, DirectionActivity::class.java))
        }

        btnFlash.setOnClickListener{
            startActivity(Intent(this, ShakeFlashActivity::class.java))
        }

        btnProx.setOnClickListener{
            startActivity(Intent(this, ProximityActivity::class.java))
        }

        btnLoc.setOnClickListener {
            startActivity(Intent(this, LocationActivity::class.java))
        }
    }
}
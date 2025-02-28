package com.example.partie1

import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imgCapt = findViewById<ImageView>(R.id.imgCheckCapteurs)
        val imgAcc = findViewById<ImageView>(R.id.imgCheckAcc)
        val imgDir = findViewById<ImageView>(R.id.imgCheckDir)
        val imgFlash = findViewById<ImageView>(R.id.imgCheckFlash)
        val imgProx = findViewById<ImageView>(R.id.imgCheckProx)
        val imgLoc = findViewById<ImageView>(R.id.imgCheckLoc)

        imgCapt.setOnClickListener {
            startActivity(Intent(this, ListeCapteursActivity::class.java))
        }

        imgAcc.setOnClickListener{
            startActivity(Intent(this, AccelerometerActivity :: class.java))
        }

        imgDir.setOnClickListener{
            startActivity(Intent(this, DirectionActivity::class.java))
        }

        imgFlash.setOnClickListener{
            startActivity(Intent(this, ShakeFlashActivity::class.java))
        }

        imgProx.setOnClickListener{
            startActivity(Intent(this, ProximityActivity::class.java))
        }

        imgLoc.setOnClickListener {
            startActivity(Intent(this, LocationActivity::class.java))
        }
    }
}
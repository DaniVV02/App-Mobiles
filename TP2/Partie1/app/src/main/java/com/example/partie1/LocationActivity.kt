package com.example.partie1

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class LocationActivity : ComponentActivity(), LocationListener {

    private lateinit var locationManager: LocationManager
    private lateinit var textViewLocation: TextView
    private lateinit var buttonOpenMaps: Button
    private var currentLatitude: Double = 0.0
    private var currentLongitude: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

        textViewLocation = findViewById(R.id.textViewLocation)
        buttonOpenMaps = findViewById(R.id.buttonOpenMaps)

        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager

        // Vérifier et demander la permission de localisation
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
        } else {
            requestLocation()
        }

        // Ouvrir Google Maps avec la position actuelle
        buttonOpenMaps.setOnClickListener {
            val uri = "geo:$currentLatitude,$currentLongitude?q=$currentLatitude,$currentLongitude"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
            intent.setPackage("com.google.android.apps.maps") // Ouvre directement Google Maps
            startActivity(intent)
        }
    }

    private fun requestLocation() {
        try {
            if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                Toast.makeText(this, "⚠️ GPS désactivé. Activez-le pour voir votre position.", Toast.LENGTH_LONG).show()
                textViewLocation.text = "⚠️ GPS désactivé. Activez-le dans les paramètres."
                buttonOpenMaps.isEnabled = false
                return
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5f, this)

        } catch (e: SecurityException) {
            textViewLocation.text = "⚠️ Permission non accordée"
        }
    }

    override fun onLocationChanged(location: Location) {
        currentLatitude = location.latitude
        currentLongitude = location.longitude
        textViewLocation.text = "📍 Latitude : $currentLatitude\n📍 Longitude : $currentLongitude"
        buttonOpenMaps.isEnabled = true
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
    override fun onProviderEnabled(provider: String) {
        Toast.makeText(this, "✅ GPS activé ! Recherche de votre position...", Toast.LENGTH_SHORT).show()
        requestLocation()
    }
    override fun onProviderDisabled(provider: String) {
        Toast.makeText(this, "⚠️ GPS désactivé. Activez-le dans les paramètres.", Toast.LENGTH_LONG).show()
        textViewLocation.text = "⚠️ GPS désactivé. Activez-le pour voir votre position."
        buttonOpenMaps.isEnabled = false
    }
}

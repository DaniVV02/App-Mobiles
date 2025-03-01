package com.example.partie1

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
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
import java.util.Locale

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

        // Pour ouvrir Google Maps avec la position actuelle
        buttonOpenMaps.setOnClickListener {
            val address = getAddress(currentLatitude, currentLongitude)
            val uri = "geo:0,0?q=${Uri.encode(address)}"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
            intent.setPackage("com.google.android.apps.maps") // ouvrir directement Google Maps
            startActivity(intent)
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

    private fun requestLocation() {
        try {
            if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                Toast.makeText(this, "GPS désactivé. Activez-le pour voir votre position.", Toast.LENGTH_LONG).show()
                textViewLocation.text = "GPS désactivé. Activez-le dans les paramètres."
                buttonOpenMaps.isEnabled = false
                return
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5f, this)

        } catch (e: SecurityException) {
            textViewLocation.text = "Permission non accordée"
        }
    }

    override fun onLocationChanged(location: Location) {
        currentLatitude = location.latitude
        currentLongitude = location.longitude
        val address = getAddress(currentLatitude, currentLongitude)

        textViewLocation.text = "📍 Latitude : $currentLatitude\n📍 Longitude : $currentLongitude\n\uD83D\uDCCC Adresse : $address"
        buttonOpenMaps.isEnabled = true
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
    override fun onProviderEnabled(provider: String) {
        Toast.makeText(this, "GPS activé ! Recherche de votre position...", Toast.LENGTH_SHORT).show()
        requestLocation()
    }
    override fun onProviderDisabled(provider: String) {
        Toast.makeText(this, "GPS désactivé. Activez-le dans les paramètres.", Toast.LENGTH_LONG).show()
        textViewLocation.text = "GPS désactivé. Activez-le pour voir votre position."
        buttonOpenMaps.isEnabled = false
    }

    private fun getAddress(latitude: Double, longitude: Double): String {
        return try {
            val geocoder = Geocoder(this, Locale.getDefault())
            val addresses = geocoder.getFromLocation(latitude, longitude, 1)
            if (addresses != null && addresses.isNotEmpty()) {
                val address = addresses[0]
                "${address.locality}, ${address.countryName}\n${address.getAddressLine(0)}"
            } else {
                "Adresse non disponible"
            }
        } catch (e: Exception) {
            "Erreur lors de la récupération de l'adresse"
        }
    }
}

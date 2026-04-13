
/*
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kirubel.findlocalbusinessesandservices.ui.theme.FindLocalBusinessesAndServicesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FindLocalBusinessesAndServicesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FindLocalBusinessesAndServicesTheme {
        Greeting("Android")
    }
}
*/


//package com.kirubel.habittracker
/*
package com.kirubel.findlocalbusinessesandservices

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_maps)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment

        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val addisAbaba = LatLng(9.03, 38.74)

        mMap.addMarker(
            MarkerOptions()
                .position(addisAbaba)
                .title("Addis Ababa")
        )

        mMap.moveCamera(
            CameraUpdateFactory.newLatLngZoom(addisAbaba, 12f)
        )
    }
}

 */
/*
package com.kirubel.findlocalbusinessesandservices

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment

        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val places = getPlaces()

        for (place in places) {
            val position = LatLng(place.lat, place.lng)

            val marker = mMap.addMarker(
                MarkerOptions()
                    .position(position)
                    .title(place.name)
                    .snippet(place.description)
            )

            marker?.tag = place
        }

        mMap.moveCamera(
            CameraUpdateFactory.newLatLngZoom(
                LatLng(9.03, 38.74),
                12f
            )
        )

        mMap.setOnMarkerClickListener { marker ->
            val place = marker.tag as Place

            Toast.makeText(
                this,
                "${place.name}\n${place.description}",
                Toast.LENGTH_LONG
            ).show()

            true
        }
    }

    private fun getPlaces(): List<Place> {
        return listOf(
            Place("Black Lion Hospital", "Hospital", 9.035, 38.752, "hospital"),
            Place("Tikur Anbessa", "Medical Center", 9.030, 38.740, "hospital"),
            Place("Hyatt Regency", "Hotel", 9.016, 38.769, "hotel"),
            Place("Sheraton Addis", "Hotel", 9.015, 38.757, "hotel"),
            Place("Café Jera", "Restaurant", 9.022, 38.746, "restaurant"),
            Place("Tomoca Coffee", "Coffee Shop", 9.031, 38.742, "restaurant"),
            Place("Dashen Bank", "Bank", 9.018, 38.748, "bank"),
            Place("Commercial Bank", "Bank", 9.025, 38.750, "bank"),
            Place("Piassa Market", "Market", 9.033, 38.739, "shop"),
            Place("Merkato", "Market", 9.036, 38.722, "shop"),
            Place("Edna Mall", "Shopping Mall", 9.010, 38.800, "shop"),
            Place("Lideta Hospital", "Hospital", 9.020, 38.730, "hospital"),
            Place("Stadium Area", "Event Zone", 9.040, 38.760, "shop"),
            Place("CMC Bank", "Bank Branch", 9.060, 38.780, "bank"),
            Place("Bole Road Café", "Cafe", 9.005, 38.805, "restaurant"),
            Place("Kazanchis Hotel", "Hotel", 9.020, 38.765, "hotel"),
            Place("Atlas Hotel", "Hotel", 9.018, 38.770, "hotel"),
            Place("Friendship Mall", "Mall", 9.007, 38.790, "shop"),
            Place("Sarbet Area", "Commercial Zone", 9.030, 38.740, "shop"),
            Place("Bole Medhanealem", "Church Area", 9.012, 38.810, "shop")
        )
    }
}

 */

package com.kirubel.findlocalbusinessesandservices

import android.os.Bundle
import android.widget.Button
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.kirubel.findlocalbusinessesandservices.data.PlacesData
import com.kirubel.findlocalbusinessesandservices.model.Place

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    private val allPlaces = PlacesData.places
    private var filteredPlaces: List<Place> = allPlaces

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment

        mapFragment.getMapAsync(this)

        setupFilters()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Marker click popup (Toast)
        mMap.setOnMarkerClickListener { marker ->
            Toast.makeText(
                this,
                "${marker.title}\n${marker.snippet}",
                Toast.LENGTH_SHORT
            ).show()
            false
        }

        filteredPlaces = allPlaces
        showMarkers(filteredPlaces)

        setupSearch()
    }

    // ---------------------------
    // FILTER BUTTONS
    // ---------------------------
    private fun setupFilters() {

        findViewById<Button>(R.id.btnAll).setOnClickListener {
            filteredPlaces = allPlaces
            showMarkers(filteredPlaces)
        }

        findViewById<Button>(R.id.btnHospital).setOnClickListener {
            filterByType("hospital")
        }

        findViewById<Button>(R.id.btnClinic).setOnClickListener {
            filterByType("clinic")
        }

        findViewById<Button>(R.id.btnRestaurant).setOnClickListener {
            filterByType("restaurant")
        }

        findViewById<Button>(R.id.btnSchool).setOnClickListener {
            filterByType("school")
        }

        findViewById<Button>(R.id.btnBank).setOnClickListener {
            filterByType("bank")
        }
    }

    private fun filterByType(type: String) {
        filteredPlaces = allPlaces.filter { it.type == type }
        showMarkers(filteredPlaces)
    }

    // ---------------------------
    // SEARCH FILTER
    // ---------------------------
    private fun setupSearch() {
        val searchView = findViewById<SearchView>(R.id.searchView)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                val query = newText?.lowercase() ?: ""

                val result = filteredPlaces.filter {
                    it.name.lowercase().contains(query) ||
                            it.description.lowercase().contains(query) ||
                            it.type.lowercase().contains(query)
                }

                showMarkers(result)
                return true
            }
        })
    }

    // ---------------------------
    // SHOW MARKERS ON MAP
    // ---------------------------
    private fun showMarkers(list: List<Place>) {

        mMap.clear()

        list.forEach { place ->
            val position = LatLng(place.latitude, place.longitude)

            mMap.addMarker(
                MarkerOptions()
                    .position(position)
                    .title(place.name)
                    .snippet("${place.type} - ${place.description}")
            )
        }

        // Move camera to first result
        list.firstOrNull()?.let {
            mMap.moveCamera(
                CameraUpdateFactory.newLatLngZoom(
                    LatLng(it.latitude, it.longitude),
                    12f
                )
            )
        }
    }
}
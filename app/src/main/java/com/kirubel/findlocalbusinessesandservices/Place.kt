package com.kirubel.findlocalbusinessesandservices

data class Place(
    val name: String,
    val description: String,
    val lat: Double,
    val lng: Double,
    val type: String
)
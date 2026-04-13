package com.kirubel.findlocalbusinessesandservices.model

data class Place(
    val name: String,
    val description: String,
    val latitude: Double,
    val longitude: Double,
    val type: String
)
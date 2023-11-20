package com.example.api.api.models

data class Hotel(
    var id: Int? = null,
    var name: String? = null,
    var adress: String? = null,
    var minimal_price: Int? = null,
    var price_for_it: String? = null,
    var rating: Int? = null,
    var rating_name: String? = null,
    var image_urls: ArrayList<String> = arrayListOf(),
    var about_the_hotel: AboutTheHotel? = AboutTheHotel(),
)

data class AboutTheHotel(
    var description: String? = null,
    var peculiarities: ArrayList<String> = arrayListOf(),
)
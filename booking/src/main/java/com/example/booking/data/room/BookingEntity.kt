package com.example.booking.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BookingEntity(
val arrival_country: String ="",
val departure: String = "",
val fuel_charge: Int = 0,
val horating: Int = 0,
val hotel_adress: String ="",
val hotel_name: String = "",
@PrimaryKey
val id: Int = 0,
val number_of_nights: Int = 0,
val nutrition: String = "",
val rating_name: String = "",
val room: String = "",
val service_charge: Int = 0,
val tour_date_start: String = "",
val tour_date_stop: String = "",
val tour_price: Int = 0
)

package com.example.booking.presentation.adapter.bookingDeparture

import com.example.util.adapter.DelegateAdapterItem

class BookingDepartureDelegate(
    val hotel_name: String,
    val departure: String,
    val arrival_country: String,
    val tour_date_start: String,
    val tour_date_stop: String,
    val number_of_nights: Int,
    val room: String,
    val nutrition: String,
) : DelegateAdapterItem {

    override fun id(): Any = BookingDepartureDelegate::class.java

    override fun content(): Any = BookingDepartureContent(
        hotel_name,
        departure,
        arrival_country,
        tour_date_start,
        tour_date_stop,
        number_of_nights,
        room,
        nutrition
    )

    override fun payload(other: Any): DelegateAdapterItem.Payloadable {
        if (other is BookingDepartureContent) {

            if (hotel_name != other.hotel_name) {
                return ChangePayload.HotelNameChanged(other.hotel_name)
            }

            if (departure != other.departure) {
                return ChangePayload.DepartureChanged(other.departure)
            }

            if (arrival_country != other.arrival_country) {
                return ChangePayload.ArrivalCountryChanged(other.arrival_country)
            }

            if (tour_date_start != other.tour_date_start) {
                return ChangePayload.TourDateStartChanged(other.tour_date_start)
            }

            if (tour_date_stop != other.tour_date_stop) {
                return ChangePayload.TourDateStopChanged(other.tour_date_stop)
            }

            if (number_of_nights != other.number_of_nights) {
                return ChangePayload.NumberOfNightsChanged(other.number_of_nights)
            }

            if (room != other.room) {
                return ChangePayload.RoomChanged(other.room)
            }

            if (nutrition != other.nutrition) {
                return ChangePayload.NutritionChanged(other.nutrition)
            }
        }
        return DelegateAdapterItem.Payloadable.None
    }


    inner class BookingDepartureContent(
        val hotel_name: String,
        val departure: String,
        val arrival_country: String,
        val tour_date_start: String,
        val tour_date_stop: String,
        val number_of_nights: Int,
        val room: String,
        val nutrition: String,
    ) {
        override fun equals(other: Any?): Boolean {

            if (other is BookingDepartureContent) {
                return hotel_name == other.hotel_name && departure == other.departure  && arrival_country == other.arrival_country &&
                        tour_date_start == other.tour_date_start &&
                        tour_date_stop == other.tour_date_stop &&
                        number_of_nights == other.number_of_nights &&
                        room == other.room &&
                        nutrition == other.nutrition
            }
            return false
        }

        override fun hashCode(): Int {
            var result = tour_date_start.hashCode()
            result = 31 * result + tour_date_stop.hashCode()
            return result
        }
    }

    sealed class ChangePayload : DelegateAdapterItem.Payloadable {
        data class HotelNameChanged(val hotel_name: String) : ChangePayload()
        data class DepartureChanged(val departure: String) : ChangePayload()
        data class ArrivalCountryChanged(val arrival_country: String) : ChangePayload()
        data class TourDateStartChanged(val tour_date_start: String) : ChangePayload()
        data class TourDateStopChanged(val tour_date_stop: String) : ChangePayload()
        data class NumberOfNightsChanged(val number_of_nights: Int) : ChangePayload()
        data class RoomChanged(val room: String) : ChangePayload()
        data class NutritionChanged(val nutrition: String) : ChangePayload()
    }
}
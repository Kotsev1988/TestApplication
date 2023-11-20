package com.example.booking.presentation.fragment

import com.example.api.api.models.Booking
import com.example.booking.domain.model.CheckFilled
import com.example.booking.domain.model.Contacts
import com.example.booking.domain.model.TouristInfo

sealed class BookingAppState{
    data class OnSuccess(
        val booking: Booking,
        val tourist: ArrayList<TouristInfo>,
        val contacts: Contacts
    ): BookingAppState()

    data class OnAddTourist(
        val booking: Booking,
        val tourist: ArrayList<TouristInfo>,
        val contacts: Contacts
    ): BookingAppState()

    data class CheckFields(
        val booking: Booking,
        val tourist: ArrayList<TouristInfo>,
        val contacts: Contacts,
        val checking: CheckFilled
    ): BookingAppState()

    data class CheckedFields(
        val booking: Booking,
        val tourist: ArrayList<TouristInfo>,
        val contacts: Contacts,
        val checking: CheckFilled
    ): BookingAppState()

    object Navigate : BookingAppState()
    object NeedAddTourist : BookingAppState()
    data class Error(val error: String) : BookingAppState()
    object Loading : BookingAppState()
}

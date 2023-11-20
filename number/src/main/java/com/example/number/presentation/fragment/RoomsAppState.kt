package com.example.number.presentation.fragment


import com.example.api.api.models.Room

sealed class RoomsAppState{
    data class OnSuccess(
        val rooms: List<Room>
    ): RoomsAppState()
    data class Error(val error: String) : RoomsAppState()
    object Loading : RoomsAppState()
}

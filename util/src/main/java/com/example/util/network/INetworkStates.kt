package com.example.util.network

import kotlinx.coroutines.flow.Flow

interface INetworkStates {
    fun isOnline(): Flow<Status>

    enum class Status{
        Available, UnAvailable,
        Losing //, Lost
    }
}
package com.example.util.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class AndroidNetworkStatus (context: Context): INetworkStates {

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private val request: NetworkRequest = NetworkRequest.Builder()
        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        .build()

    override fun isOnline(): Flow<INetworkStates.Status> {
        return callbackFlow {
            val networkStatusCallback = object : ConnectivityManager.NetworkCallback() {


                override fun onUnavailable() {
                    super.onUnavailable()

                    launch { send(INetworkStates.Status.UnAvailable) }
                }

                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    launch { send(INetworkStates.Status.Available) }
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    //launch { send(INetworkStates.Status.Lost) }
                }

                override fun onLosing(network: Network, maxMsToLive: Int) {
                    super.onLosing(network, maxMsToLive)
                    launch { send(INetworkStates.Status.Losing) }
                }

            }
            connectivityManager.registerNetworkCallback(request, networkStatusCallback)
            if (connectivityManager.activeNetwork == null){
                trySend((INetworkStates.Status.UnAvailable) )
            }

            awaitClose {
                connectivityManager.unregisterNetworkCallback(networkStatusCallback)
            }
        }.distinctUntilChanged()
    }

}
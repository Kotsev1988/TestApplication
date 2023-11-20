package com.example.number.presentation.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.number.domain.use_case.GetHotelRoomUseCase
import com.example.util.Resource
import com.example.util.network.INetworkStates
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Provider

class RoomsViewModel(
    private val hotelRoomUseCase: GetHotelRoomUseCase,
    private val networkStatus: INetworkStates,
): ViewModel() {

    private val _listOfRooms = MutableLiveData<RoomsAppState>()
    val listOfRooms: LiveData<RoomsAppState>
        get() = _listOfRooms

    fun init(){

        networkStatus.isOnline().onEach {
            when (it) {
                INetworkStates.Status.Available -> {
                    getRemoteHotelRoomsResponse()
                }

                INetworkStates.Status.UnAvailable -> {
                    _listOfRooms.postValue(RoomsAppState.Error("Check your Internet connection"))
                    getLocalCacheHotelRoomsResponse()
                }

                INetworkStates.Status.Losing -> {
                    RoomsAppState.Error("Error load rooms")
                }
            }
        }.launchIn(viewModelScope)
    }

    private suspend fun getLocalCacheHotelRoomsResponse() {
        hotelRoomUseCase.getHotelRoomsLocal().flowOn(Dispatchers.IO).onEach {room ->
            when (room) {
                is Resource.Success -> {
                    _listOfRooms.postValue(room.data?.let { data ->
                        RoomsAppState.OnSuccess(
                            rooms = data.rooms
                        )
                    })
                }

                is Resource.Error -> {
                    _listOfRooms.postValue(
                        RoomsAppState.Error(
                            room.message ?: "An unexpected error"
                        )
                    )
                }

                is Resource.Loading -> {
                    _listOfRooms.postValue(
                        RoomsAppState.Loading
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private suspend fun getRemoteHotelRoomsResponse() {
        hotelRoomUseCase.getHotelRoomsRemote().flowOn(Dispatchers.IO).onEach { response ->
            when (response) {
                is Resource.Success -> {
                    _listOfRooms.postValue(response.data?.let { data ->
                        RoomsAppState.OnSuccess(
                            rooms = data.rooms
                        )
                    })
                }

                is Resource.Error -> {
                    _listOfRooms.postValue(
                        RoomsAppState.Error(
                            response.message ?: "An unexpected error"
                        )
                    )
                }

                is Resource.Loading -> {
                    _listOfRooms.postValue(
                        RoomsAppState.Loading
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    class Factory @Inject constructor(
        private val hotelRoomUseCase: Provider<GetHotelRoomUseCase>,
        private val networkStatus: Provider<INetworkStates>,
    ): ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass== RoomsViewModel::class.java)
            return RoomsViewModel(
                hotelRoomUseCase.get(),
                networkStatus.get()
            ) as T
        }
    }
}
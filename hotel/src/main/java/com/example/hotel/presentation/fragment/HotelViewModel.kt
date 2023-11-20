package com.example.hotel.presentation.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.hotel.domain.use_case.GetHotelUseCase
import com.example.util.Resource
import com.example.util.network.INetworkStates
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Provider

class HotelViewModel(
    private val hotelUseCase: GetHotelUseCase,
    private val networkStatus: INetworkStates,
) : ViewModel() {

    private val _hotel: MutableLiveData<HotelAppState> = MutableLiveData()
    val hotelItem: LiveData<HotelAppState>
        get() {
            return _hotel
        }

    private val _clickEvent: MutableLiveData<Event<String>> = MutableLiveData()
    val clickEvent: LiveData<Event<String>>
        get() {
            return _clickEvent
        }

    fun init() {
            networkStatus.isOnline().onEach {
                when (it) {
                    INetworkStates.Status.Available -> {
                        getRemoteHotelResponse()
                    }

                    INetworkStates.Status.UnAvailable -> {
                        _hotel.postValue(HotelAppState.Error("Check your Internet connection"))
                        getLocalCacheHotelResponse()
                    }

                    INetworkStates.Status.Losing -> {
                        HotelAppState.Error("Error to Load Hotel")
                    }
                }
            }.launchIn(viewModelScope)
    }

    private suspend fun getRemoteHotelResponse(){
        hotelUseCase.getHotelsRemote().flowOn(Dispatchers.IO).onEach { response ->
            when (response) {
                is Resource.Success -> {
                    _hotel.postValue(response.data?.let { data ->
                        HotelAppState.OnSuccess(
                            hotel = data
                        )
                    })
                }

                is Resource.Error -> {
                    _hotel.postValue(
                        HotelAppState.Error(
                            response.message ?: "An unexpected error"
                        )
                    )
                }

                is Resource.Loading -> {
                    _hotel.postValue(
                        HotelAppState.Loading
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private suspend fun getLocalCacheHotelResponse(){

            hotelUseCase.getFromLocal().flowOn(Dispatchers.IO).onEach {room ->
                when (room) {
                    is Resource.Success -> {
                        _hotel.postValue(room.data?.let { data ->
                            HotelAppState.OnSuccess(
                                hotel = data
                            )
                        })
                    }

                    is Resource.Error -> {
                        _hotel.postValue(
                            HotelAppState.Error(
                                room.message ?: "An unexpected error"
                            )
                        )
                    }

                    is Resource.Loading -> {
                        _hotel.postValue(
                            HotelAppState.Loading
                        )
                    }
                }
            }.launchIn(viewModelScope)
    }

    fun navigateToRooms(hotelName: String) {
        _clickEvent.value = Event(hotelName)
    }

    class Factory @Inject constructor(
        private val hotelUseCase: Provider<GetHotelUseCase>,
        private val networkStatus: Provider<INetworkStates>,
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass == HotelViewModel::class.java)
            return HotelViewModel(
                hotelUseCase.get(),
                networkStatus.get()
            ) as T
        }
    }
}
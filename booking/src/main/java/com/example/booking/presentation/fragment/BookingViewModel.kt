package com.example.booking.presentation.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.api.api.models.Booking
import com.example.booking.domain.model.CheckFilled
import com.example.booking.domain.model.Contacts
import com.example.booking.domain.model.TouristInfo
import com.example.booking.domain.use_case.GetBookingUseCase
import com.example.util.Resource
import com.example.util.network.INetworkStates
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Provider

class BookingViewModel(
    private val hotelBookingUseCase: GetBookingUseCase,
    private val networkStatus: INetworkStates,
) : ViewModel() {

    private val _booking: MutableLiveData<BookingAppState> = MutableLiveData()
    val bookingItem: LiveData<BookingAppState>
        get() = _booking

    private val _checkingFields: MutableLiveData<Boolean> = MutableLiveData()
    var checking = CheckFilled(false)
    var id = 0

    private var tourists: ArrayList<TouristInfo> = arrayListOf()
    var bookingBody = Booking()
    var contacts: Contacts = Contacts("", "")
    private val _clickEvent: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val clickEvent: LiveData<Event<Boolean>>
        get() {
            return _clickEvent
        }

    fun init() {
    contacts = Contacts("", "")
    tourists.clear()
    _checkingFields.postValue(false)

        networkStatus.isOnline().onEach {
            when (it) {
                INetworkStates.Status.Available -> {
                    getRemoteHotelResponse()
                }

                INetworkStates.Status.UnAvailable -> {
                    _booking.postValue(BookingAppState.Error("Check your Internet connection"))
                    getLocalCacheHotelResponse()
                }

                INetworkStates.Status.Losing -> {
                    BookingAppState.Error("Error to Load Hotel")
                }
            }
        }.launchIn(viewModelScope)
    }

    private suspend fun getRemoteHotelResponse(){
        hotelBookingUseCase.getBookingRemote().flowOn(Dispatchers.IO).onEach { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let {
                        bookingBody = it
                    }

                    _booking.postValue(response.data?.let { data ->
                        BookingAppState.OnSuccess(
                            data, tourists, contacts
                        )
                    })
                }

                is Resource.Error -> {
                    _booking.postValue(
                        BookingAppState.Error(
                            response.message ?: "An unexpected error"
                        )
                    )
                }

                is Resource.Loading -> {
                    _booking.postValue(
                        BookingAppState.Loading
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private suspend fun getLocalCacheHotelResponse(){

        hotelBookingUseCase.getBookingLocal().flowOn(Dispatchers.IO).onEach {room ->
            when (room) {
                is Resource.Success -> {
                    room.data?.let {
                        bookingBody = it
                    }
                    _booking.postValue(room.data?.let { data ->
                        BookingAppState.OnSuccess(
                            data, tourists, contacts
                        )
                    })
                }

                is Resource.Error -> {
                    _booking.postValue(
                        BookingAppState.Error(
                            room.message ?: "An unexpected error"
                        )
                    )
                }

                is Resource.Loading -> {
                    _booking.postValue(
                        BookingAppState.Loading
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
    fun addTourist() {

        tourists.add(TouristInfo(id++))
        _booking.value = BookingAppState.OnAddTourist(bookingBody, tourists, contacts)
    }
    fun setCollapsed(collapsed: Boolean, position: Int) {
         tourists[position] = tourists[position].copy(collapsed = collapsed)
    }
    fun checkFields() {

        tourists.forEach {
            _checkingFields.value = (!it.first_name.isNullOrEmpty()  && !it.last_name.isNullOrEmpty()
                    && !it.birth_date.isNullOrEmpty() && !it.citizenship.isNullOrEmpty()
                    && !it.document_number.isNullOrEmpty() && !it.validate.isNullOrEmpty())
        }

        if (tourists.size == 0){
            _booking.value = BookingAppState.NeedAddTourist
        }

        if (_checkingFields.value == true && !contacts.email.isNullOrEmpty() && !contacts.email.isNullOrEmpty()){
            _clickEvent.value = Event(true)
        }else {
            _booking.value =
                BookingAppState.CheckFields(bookingBody, tourists, contacts, checking.copy(true))
        }
    }

    fun setPhone(it: String) {
       contacts = contacts.copy(phone = it)
    }

    fun setEmail(it: String) {
       contacts = contacts.copy(email = it)
    }

    fun setFirstName(firstName: String, pos: Int) {
        tourists[pos] = tourists[pos].copy(first_name = firstName)
    }

    fun setLastName(lastName: String, pos: Int) {
        tourists[pos] = tourists[pos].copy(last_name = lastName)
    }

    fun setBirthday(birthday: String, pos: Int) {
        tourists[pos] = tourists[pos].copy(birth_date = birthday)
    }

    fun setCitizenship(citizenship: String, pos: Int) {
        tourists[pos] = tourists[pos].copy(citizenship = citizenship)
    }

    fun setPassportNumber(passportNumber: String, pos: Int) {
        tourists[pos] = tourists[pos].copy(document_number = passportNumber)
    }

    fun setValidate(validate: String, pos: Int) {
        tourists[pos] = tourists[pos].copy(validate = validate)
    }
    fun onCheckedFields(it: Boolean) {
        tourists.forEach {
            it.checkFeilds = false
        }
        _booking.value =
            BookingAppState.CheckedFields(bookingBody, tourists, contacts, checking.copy(false))
    }

    class Factory @Inject constructor(
        private val hotelBookingUseCase:  Provider<GetBookingUseCase>,
        private val networkStatus: Provider<INetworkStates>,
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass == BookingViewModel::class.java)
            return BookingViewModel(
                hotelBookingUseCase.get(),
                networkStatus.get()
            ) as T
        }
    }
}


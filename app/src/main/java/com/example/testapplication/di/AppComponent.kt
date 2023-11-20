package com.example.testapplication.di

import com.example.booking.di.BookingComponent
import com.example.booking.domain.IBookingCache
import com.example.booking.domain.IGetBooking
import com.example.booking.domain.use_case.GetBookingUseCase
import com.example.hotel.domain.IGetHotel
import com.example.number.domain.IGetRooms
import com.example.hotel.di.HotelComponent
import com.example.hotel.domain.IHotelCache
import com.example.hotel.domain.use_case.GetHotelUseCase
import com.example.number.di.NumberComponent
import com.example.number.domain.IRoomCache
import com.example.number.domain.use_case.GetHotelRoomUseCase
import com.example.testapplication.di.modules.ApiModule
import com.example.testapplication.di.modules.AppModule
import com.example.testapplication.di.modules.DataModule
import com.example.testapplication.di.modules.DatabaseModule
import com.example.testapplication.di.scopes.MainActivityScope
import com.example.testapplication.presentation.activity.MainActivity
import com.example.util.network.INetworkStates
import dagger.Component

@MainActivityScope
@Component(
    modules = [
    AppModule::class,
    ApiModule::class,
    DataModule::class,
    DatabaseModule::class
    ]
)
interface AppComponent: HotelComponent.ArticleDeps, NumberComponent.RoomsDeps, BookingComponent.BookingDeps {
    override val hotel: IGetHotel
    override val hotelCache: IHotelCache
    override val networkStatus: INetworkStates

    override val useCase: GetHotelUseCase
    override val hotelRooms: IGetRooms
    override val hotelRoomCache: IRoomCache
    override val hotelRoomsUseCase: GetHotelRoomUseCase

    override val booking: IGetBooking
    override val bookingCache: IBookingCache
    override val bookingUseCase: GetBookingUseCase

    fun inject(mainActivity: MainActivity)
}
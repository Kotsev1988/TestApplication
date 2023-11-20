package com.example.testapplication.di.modules

import com.example.api.api.IHotelAPI
import com.example.booking.data.GetBookingImpl
import com.example.booking.domain.IGetBooking
import com.example.hotel.domain.IGetHotel
import com.example.number.domain.IGetRooms
import com.example.hotel.data.GetHotelImpl
import com.example.hotel.data.room.HotelDao
import com.example.hotel.domain.use_case.GetHotelUseCase
import com.example.number.data.GetRoomsImpl
import com.example.testapplication.di.scopes.MainActivityScope
import dagger.Module
import dagger.Provides

@Module
class DataModule {
    @MainActivityScope
    @Provides
    fun getHotel(api: IHotelAPI
    ): IGetHotel = GetHotelImpl(api)

    @MainActivityScope
    @Provides
    fun getRooms(api: IHotelAPI
    ): IGetRooms = GetRoomsImpl(api)

    @MainActivityScope
    @Provides
    fun getBooking(
        api: IHotelAPI
    ): IGetBooking = GetBookingImpl(api)

    @MainActivityScope
    @Provides
    fun provideUseCase(
        repository: IGetHotel,
        hotelDao: HotelDao
    ): GetHotelUseCase = GetHotelUseCase(repository, hotelDao)
}
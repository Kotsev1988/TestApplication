package com.example.testapplication.di.modules

import android.content.Context
import androidx.room.Room
import com.example.booking.data.GetBookingCacheImpl
import com.example.booking.data.room.BookingDao
import com.example.booking.domain.IBookingCache
import com.example.hotel.data.GetHotelCacheImpl
import com.example.hotel.data.room.HotelDao
import com.example.hotel.domain.IHotelCache
import com.example.number.data.room.GetHotelRoomsImpl
import com.example.number.data.room.RoomDao
import com.example.number.domain.IRoomCache
import com.example.testapplication.data.Database
import com.example.testapplication.di.scopes.MainActivityScope
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @MainActivityScope
    @Provides
    fun database(context: Context) =
        Room.databaseBuilder(context, Database::class.java, Database.DB_NAME).build()

    @MainActivityScope
    @Provides
    fun provideHomeDao(appDatabase: Database): HotelDao = appDatabase.hotelDao

    @MainActivityScope
    @Provides
    fun provideHotelRoomsDao(appDatabase: Database): RoomDao = appDatabase.roomDao

    @MainActivityScope
    @Provides
    fun provideBookingDao(appDatabase: Database): BookingDao = appDatabase.bookingDao


    @MainActivityScope
    @Provides
    fun hotelCache(db: HotelDao): IHotelCache = GetHotelCacheImpl(db)

    @MainActivityScope
    @Provides
    fun hotelRoomsCache(db: RoomDao): IRoomCache = GetHotelRoomsImpl(db)

    @MainActivityScope
    @Provides
    fun hotelBookingCache(db: BookingDao): IBookingCache = GetBookingCacheImpl(db)

}
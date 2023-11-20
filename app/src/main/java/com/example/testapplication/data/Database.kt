package com.example.testapplication.data

import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.booking.data.room.BookingDao
import com.example.booking.data.room.BookingEntity
import com.example.hotel.data.room.AboutHotelConverter
import com.example.hotel.data.room.AboutHotelEntity
import com.example.hotel.data.room.HotelDao
import com.example.hotel.data.room.HotelEntity
import com.example.hotel.data.room.ImageUrlConverter
import com.example.number.data.room.PeculiaritiesConverter
import com.example.number.data.room.RoomDao
import com.example.number.data.room.RoomEntity


@androidx.room.Database(entities = [HotelEntity::class, RoomEntity::class, BookingEntity::class, AboutHotelEntity::class], version = 4)
@TypeConverters(
    ImageUrlConverter::class,
    AboutHotelConverter::class,
    ImageUrlConverter::class,
    PeculiaritiesConverter::class
)
abstract class Database:  RoomDatabase() {

    abstract val hotelDao: HotelDao
    abstract val roomDao: RoomDao
    abstract val bookingDao: BookingDao



    companion object {
        const val DB_NAME = "database.db"
    }
}
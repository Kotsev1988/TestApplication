package com.example.hotel.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface HotelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(hotel: HotelEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg hotel: HotelEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(hotel: List<HotelEntity>)

    @Update
    suspend fun update(hotel: HotelEntity)

    @Update
    suspend fun update(vararg hotel: HotelEntity)

    @Update
    suspend fun update(hotel: List<HotelEntity>)

    @Delete
    suspend fun delete(hotel: HotelEntity)

    @Delete
    suspend fun delete(vararg hotel: HotelEntity)

    @Delete
    suspend fun delete(hotel: List<HotelEntity>)

    @Query("SELECT * FROM HotelEntity")
    fun getAllHotels(): HotelEntity
}
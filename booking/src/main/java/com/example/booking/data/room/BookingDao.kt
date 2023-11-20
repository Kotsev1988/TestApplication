package com.example.booking.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface BookingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(bookingEntity: BookingEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg bookingEntity: BookingEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(bookingEntity: List<BookingEntity>)

    @Update
    suspend fun update(bookingEntity: BookingEntity)

    @Update
    suspend fun update(vararg bookingEntity: BookingEntity)

    @Update
    suspend fun update(bookingEntity: List<BookingEntity>)

    @Delete
    suspend fun delete(bookingEntity: BookingEntity)

    @Delete
    suspend fun delete(vararg bookingEntity: BookingEntity)

    @Delete
    suspend fun delete(bookingEntity: List<BookingEntity>)

    @Query("SELECT * FROM BookingEntity")
    fun getAllBooking(): BookingEntity
}
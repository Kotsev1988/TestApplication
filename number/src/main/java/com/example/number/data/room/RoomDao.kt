package com.example.number.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
@Dao
interface RoomDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(hotelRoom: RoomEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg hotelRoom: RoomEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(hotelRoom: List<RoomEntity>)

    @Update
    suspend fun update(hotelRoom: RoomEntity)

    @Update
    suspend fun update(vararg hotelRoom: RoomEntity)

    @Update
    suspend fun update(hotelRoom: List<RoomEntity>)

    @Delete
    suspend fun delete(hotelRoom: RoomEntity)

    @Delete
    suspend fun delete(vararg hotelRoom: RoomEntity)

    @Delete
    suspend fun delete(hotelRoom: List<RoomEntity>)

    @Query("SELECT * FROM RoomEntity")
    fun getAllHotels(): List<RoomEntity>
}
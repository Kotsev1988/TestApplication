package com.example.number.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity
data class RoomEntity(
    @PrimaryKey
    val id: Int,
    @TypeConverters(ImagesConverter::class)
    val image_urls: List<String>,
    val name: String,
    @TypeConverters(PeculiaritiesConverter::class)
    val peculiarities: List<String>,
    val price: Int,
    val price_per: String
)

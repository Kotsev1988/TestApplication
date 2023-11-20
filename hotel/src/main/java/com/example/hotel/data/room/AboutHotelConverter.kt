package com.example.hotel.data.room

import androidx.room.TypeConverter
import com.example.api.api.models.AboutTheHotel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class AboutHotelConverter {
    @TypeConverter
    fun fromAboutTheHotel( data: AboutTheHotel): String{

        val gson = Gson()
        val type: Type = object :
            TypeToken<AboutTheHotel?>() {}.type
        return gson.toJson(data, type)
    }

    @TypeConverter
    fun toAboutTheHotel(data: String): AboutTheHotel? {

        val gson = Gson()
        val type: Type = object :
            TypeToken<AboutTheHotel?>() {}.type

        val files = gson.fromJson<AboutTheHotel>(data, type)
        return files
    }
}
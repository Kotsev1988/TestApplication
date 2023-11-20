package com.example.hotel.data.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class ImageUrlConverter {
    @TypeConverter
    fun fromList( data: ArrayList<String>): String{
        val gson = Gson()
        val type: Type = object :
            TypeToken<ArrayList<String>?>() {}.type
        return gson.toJson(data, type)
    }

    @TypeConverter
    fun toTegs(data: String): ArrayList<String>? {
        val gson = Gson()
        val type: Type = object :
            TypeToken<ArrayList<String>?>() {}.type

        val files = gson.fromJson<ArrayList<String>>(data, type)
        return files
    }
}
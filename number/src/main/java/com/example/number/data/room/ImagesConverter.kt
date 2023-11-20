package com.example.number.data.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class ImagesConverter {
    @TypeConverter
    fun fromList( list: List<String>): String{
        val gson = Gson()
        val type: Type = object :
            TypeToken<List<String>?>() {}.type
        return gson.toJson(list, type)
    }

    @TypeConverter
    fun toList(data: String): List<String>? {
        val gson = Gson()
        val type: Type = object :
            TypeToken<List<String>?>() {}.type

        val files = gson.fromJson<List<String>>(data, type)
        return files
    }
}
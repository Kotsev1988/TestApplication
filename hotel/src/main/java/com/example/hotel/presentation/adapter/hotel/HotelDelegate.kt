package com.example.hotel.presentation.adapter.hotel

import com.example.api.api.models.Hotel
import com.example.util.adapter.DelegateAdapterItem

class HotelDelegate(
    val hotels: Hotel
): DelegateAdapterItem {
    override fun id(): Any = Hotel::class.java

    override fun content(): Any  = HotelsContent(hotels)

    override fun payload(other: Any): DelegateAdapterItem.Payloadable {
        if (other is HotelsContent){
            if (hotels != other.hotels){
                return ChangePayload.HotelChangePayLoad(other.hotels)
            }
        }
        return DelegateAdapterItem.Payloadable.None
    }

    inner class HotelsContent(
        val hotels: Hotel
    ){
        override fun equals(other: Any?): Boolean {
            if (other is HotelsContent){
                return hotels == other.hotels
            }
            return false
        }

        override fun hashCode(): Int {
            var result = hotels.hashCode()
            result = 31 * result + hotels.hashCode()
            return result
        }
    }

    sealed class ChangePayload: DelegateAdapterItem.Payloadable {
        data class HotelChangePayLoad (val hotels: Hotel): ChangePayload()
    }

}
package com.example.hotel.presentation.adapter.adoutHotel

import com.example.api.api.models.AboutTheHotel
import com.example.util.adapter.DelegateAdapterItem

class AboutHotelDelegate(
    val about_the_hotel: AboutTheHotel
): DelegateAdapterItem {

    override fun id(): Any = AboutHotelDelegate::class.java

    override fun content(): Any  = AboutHotelsContent(about_the_hotel)

    override fun payload(other: Any): DelegateAdapterItem.Payloadable {
        if (other is AboutHotelsContent){
            if (about_the_hotel != other.about_the_hotel){
                return ChangePayload.AboutHotelChangePayLoad(other.about_the_hotel)
            }
        }
        return DelegateAdapterItem.Payloadable.None
    }

    inner class AboutHotelsContent(
        val about_the_hotel: AboutTheHotel
    ){
        override fun equals(other: Any?): Boolean {
            if (other is AboutHotelsContent){
                return about_the_hotel == other.about_the_hotel
            }
            return false
        }

        override fun hashCode(): Int {
            var result = about_the_hotel.hashCode()
            result = 31 * result + about_the_hotel.hashCode(

            )
            return result
        }
    }

    sealed class ChangePayload: DelegateAdapterItem.Payloadable {
        data class AboutHotelChangePayLoad (val about_the_hotel: AboutTheHotel): ChangePayload()
    }
}
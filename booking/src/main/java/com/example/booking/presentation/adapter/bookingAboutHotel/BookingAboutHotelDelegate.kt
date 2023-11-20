package com.example.booking.presentation.adapter.bookingAboutHotel

import com.example.util.adapter.DelegateAdapterItem

class BookingAboutHotelDelegate(
    val horating: Int,
    val rating_name: String ,
    val hotel_adress: String,
    val hotel_name: String,
): DelegateAdapterItem {


    override fun id(): Any = BookingAboutHotelDelegate::class.toString()

    override fun content(): Any = BookingAboutHotelContent(horating,rating_name, hotel_adress, hotel_name)

    override fun payload(other: Any): DelegateAdapterItem.Payloadable {
        if (other is BookingAboutHotelContent){
            if (horating != other.horating) {
                return ChangePayload.HoratingChanged(other.horating)
            }

            if (rating_name != other.rating_name) {
                return ChangePayload.HoratingNameChanged(other.rating_name)
            }

            if (hotel_adress != other.hotel_adress) {
                return ChangePayload.HotelAddressChanged(other.hotel_adress)
            }

            if (hotel_name != other.hotel_name) {
                return ChangePayload.HotelNameChanged(other.hotel_name)
            }
        }
        return super.payload(other)
    }

    inner class BookingAboutHotelContent(
        val horating: Int,
        val rating_name: String ,
        val hotel_adress: String,
        val hotel_name: String,
    ){
        override fun equals(other: Any?): Boolean {

            if (other is BookingAboutHotelContent){
                return horating == other.horating && hotel_adress == other.hotel_adress && hotel_name == other.hotel_name
            }
            return false
        }

        override fun hashCode(): Int {
            var result = hotel_name.hashCode()
            result = 31 * result + hotel_adress.hashCode()
            return result
        }
    }

    sealed class ChangePayload: DelegateAdapterItem.Payloadable {
        data class HoratingChanged(val horating: Int): ChangePayload()
        data class HoratingNameChanged(val rating_name: String): ChangePayload()
        data class HotelNameChanged(val hotel_name: String): ChangePayload()
        data class HotelAddressChanged(val hotel_address: String): ChangePayload()
    }
}
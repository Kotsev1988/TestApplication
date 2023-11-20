package com.example.booking.presentation.adapter.bookingTourists

import com.example.booking.domain.model.CheckFilled
import com.example.booking.domain.model.TouristInfo
import com.example.util.adapter.DelegateAdapterItem

class BookingTouristsDelegate(
    val tourists: ArrayList<TouristInfo>,
    var isFilled: CheckFilled,
) : DelegateAdapterItem {
    override fun id(): Any = BookingTouristsDelegate::class.java
    override fun content(): Any = BookingTouristContent(tourists, isFilled)
    override fun payload(other: Any): DelegateAdapterItem.Payloadable {
        if (other is BookingTouristsDelegate) {

            if (tourists != other.tourists) {
                return ChangePayload.TouristsChanged(other.tourists, isFilled)
            }
            if (isFilled != other.isFilled) {
                return ChangePayload.TouristsCheckChanged(tourists, other.isFilled)
            }
        }
        return super.payload(other)
    }

    inner class BookingTouristContent(
        val tourists: ArrayList<TouristInfo>,
        val isFilled: CheckFilled,
    ) {
        override fun equals(other: Any?): Boolean {
            if (other is BookingTouristContent) {
                return tourists == other.tourists && isFilled == other.isFilled
            }
            return false
        }

        override fun hashCode(): Int {
            var result = tourists.hashCode()
            result = 31 * result + isFilled.hashCode()
            return result
        }
    }

    sealed class ChangePayload : DelegateAdapterItem.Payloadable {
        data class TouristsChanged(
            val tourists: ArrayList<TouristInfo>,
            val isFilled: CheckFilled,
        ) : ChangePayload()

        data class TouristsCheckChanged(
            val tourists: ArrayList<TouristInfo>,
            val isFilled: CheckFilled,
        ) : ChangePayload()
    }
}




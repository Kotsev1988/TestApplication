package com.example.booking.presentation.adapter.bookingCosts

import com.example.util.adapter.DelegateAdapterItem

class BookingCostsDelegate(
    val tour_price: Int,
    val fuel_charge: Int,
    val service_charge: Int,
) : DelegateAdapterItem {
    override fun id(): Any = BookingCostsDelegate::class.java

    override fun content(): Any  = BookingCostsContent(tour_price, fuel_charge, service_charge)

    override fun payload(other: Any): DelegateAdapterItem.Payloadable {
        if (other is BookingCostsDelegate.BookingCostsContent){
            if (tour_price != other.tour_price) {
                return ChangePayload.BookingTourPriceDelegate(other.tour_price)
            }

            if (fuel_charge != other.fuel_charge) {
                return ChangePayload.BookingFuelChargeDelegate(other.fuel_charge)
            }

            if (service_charge != other.service_charge) {
                return ChangePayload.BookingServiceChargeDelegate(other.service_charge)
            }
        }
        return super.payload(other)
    }

    inner class BookingCostsContent(
        val tour_price: Int,
        val fuel_charge: Int,
        val service_charge: Int
    ){
        override fun equals(other: Any?): Boolean {

            if (other is BookingCostsContent){
                return tour_price == other.tour_price && fuel_charge == other.fuel_charge && service_charge == other.service_charge
            }
            return false
        }

        override fun hashCode(): Int {
            var result = tour_price.hashCode()
            result = 31 * result + fuel_charge.hashCode()
            return result
        }
    }

    sealed class ChangePayload: DelegateAdapterItem.Payloadable {
        data class BookingTourPriceDelegate(val tour_price: Int,): ChangePayload()
        data class BookingFuelChargeDelegate(val fuel_charge: Int): ChangePayload()
        data class BookingServiceChargeDelegate(val service_charge: Int): ChangePayload()
    }

}
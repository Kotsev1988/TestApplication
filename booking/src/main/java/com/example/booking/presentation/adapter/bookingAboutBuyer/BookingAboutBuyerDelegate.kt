package com.example.booking.presentation.adapter.bookingAboutBuyer

import com.example.booking.domain.model.Contacts
import com.example.util.adapter.DelegateAdapterItem

class BookingAboutBuyerDelegate(
    val contacts: Contacts,
    val checkFilledContacts: Boolean
): DelegateAdapterItem {
    override fun id(): Any = BookingAboutBuyerDelegate::class.java

    override fun content(): Any = BookingAboutBuyerContent(contacts, checkFilledContacts)

    override fun payload(other: Any): DelegateAdapterItem.Payloadable {
        if (other is BookingAboutBuyerContent){
            if (contacts != other.contacts && checkFilledContacts != other.checkFilled){
                return ChangePayload.ContactsChangePayLoad(other.contacts, other.checkFilled)
            }
        }
        return DelegateAdapterItem.Payloadable.None
    }
    inner class BookingAboutBuyerContent(
        val contacts: Contacts,
        val checkFilled: Boolean
    ){
        override fun equals(other: Any?): Boolean {

            if (other is BookingAboutBuyerContent){
                return contacts == other.contacts && checkFilled == other.checkFilled
            }
            return false
        }

        override fun hashCode(): Int {
            var result = contacts.hashCode()
            result = 31 * result + contacts.hashCode(
            )
            return result
        }
    }

    sealed class ChangePayload: DelegateAdapterItem.Payloadable {
        data class ContactsChangePayLoad (val contacts: Contacts, val checkFilled: Boolean): ChangePayload()
    }
}



package com.example.util.adapter

interface DelegateAdapterItem {
    fun id(): Any

    fun content(): Any

    fun payload(other: Any): Payloadable = Payloadable.None


    interface Payloadable{
        object None: Payloadable
    }
}
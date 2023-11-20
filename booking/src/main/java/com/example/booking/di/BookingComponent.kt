package com.example.booking.di

import androidx.annotation.RestrictTo
import androidx.lifecycle.ViewModel
import com.example.booking.di.scpoes.BookingScope
import com.example.booking.domain.IBookingCache
import com.example.booking.presentation.fragment.BookingFragment
import com.example.booking.domain.IGetBooking
import com.example.booking.domain.use_case.GetBookingUseCase
import com.example.util.network.INetworkStates
import dagger.Component
import kotlin.properties.Delegates

@BookingScope
@Component(
    dependencies = [BookingComponent.BookingDeps::class]
)
interface BookingComponent {

    interface BookingDeps{
        val booking: IGetBooking
        val bookingCache: IBookingCache
        val networkStatus: INetworkStates
        val bookingUseCase: GetBookingUseCase
    }



    fun inject(bookingFragment: BookingFragment)
}

interface BookingDepsProvider{
    @get:RestrictTo(RestrictTo.Scope.LIBRARY)
    val deps: BookingComponent.BookingDeps

    companion object: BookingDepsProvider by BookingDepsStore
}

object BookingDepsStore: BookingDepsProvider{
    override var deps: BookingComponent.BookingDeps by Delegates.notNull()

}

internal  class BookingComponentViewModel: ViewModel(){
    val newBookingComponent: BookingComponent = DaggerBookingComponent.builder().bookingDeps(BookingDepsStore.deps).build()
}
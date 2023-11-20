package com.example.number.di

import androidx.annotation.RestrictTo
import androidx.lifecycle.ViewModel
import com.example.number.di.scopes.NumberScope
import com.example.number.domain.IGetRooms
import com.example.number.domain.IRoomCache
import com.example.number.domain.use_case.GetHotelRoomUseCase
import com.example.number.presentation.fragment.RoomsFragment
import com.example.util.network.INetworkStates
import dagger.Component
import kotlin.properties.Delegates

@NumberScope
@Component(
    dependencies = [NumberComponent.RoomsDeps::class]
)
interface NumberComponent {

    interface RoomsDeps{
        val hotelRooms: IGetRooms
        val hotelRoomCache: IRoomCache
        val networkStatus: INetworkStates
        val hotelRoomsUseCase: GetHotelRoomUseCase
    }

    fun inject(roomsFragment: RoomsFragment)
}

interface RoomsDepsProvider{
    @get:RestrictTo(RestrictTo.Scope.LIBRARY)
    val deps: NumberComponent.RoomsDeps

    companion object: RoomsDepsProvider by RoomsDepsStore
}

object RoomsDepsStore: RoomsDepsProvider{
    override var deps: NumberComponent.RoomsDeps by Delegates.notNull()

}

internal  class RoomsComponentViewModel: ViewModel(){
    val newRoomsComponent: NumberComponent = DaggerNumberComponent.builder().roomsDeps(RoomsDepsProvider.deps).build()
}
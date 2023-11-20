package com.example.hotel.di

import androidx.annotation.RestrictTo
import androidx.lifecycle.ViewModel
import com.example.hotel.di.scpoes.HotelScope
import com.example.hotel.domain.IGetHotel
import com.example.hotel.domain.IHotelCache
import com.example.hotel.domain.use_case.GetHotelUseCase
import com.example.hotel.presentation.fragment.HotelFragment
import com.example.util.network.INetworkStates
import dagger.Component
import kotlin.properties.Delegates

@HotelScope
@Component(
    dependencies = [HotelComponent.ArticleDeps::class]
)
interface HotelComponent {

    interface ArticleDeps{
        val hotel: IGetHotel
        val hotelCache: IHotelCache
        val networkStatus: INetworkStates
        val useCase: GetHotelUseCase
    }

    fun inject(hotelFragment: HotelFragment)
}

interface ArticleDepsProvider{
    @get:RestrictTo(RestrictTo.Scope.LIBRARY)
    val deps: HotelComponent.ArticleDeps

    companion object: ArticleDepsProvider by ArticleDepsStore
}

object ArticleDepsStore: ArticleDepsProvider{
    override var deps: HotelComponent.ArticleDeps by Delegates.notNull()

}

internal  class ArticleComponentViewModel: ViewModel(){
    val newHotelComponent: HotelComponent = DaggerHotelComponent.builder().articleDeps(ArticleDepsProvider.deps).build()
}
package com.example.hotel.domain.use_case

import com.example.api.api.models.Hotel
import com.example.hotel.data.room.HotelDao
import com.example.hotel.data.room.HotelEntity
import com.example.hotel.domain.IGetHotel
import com.example.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetHotelUseCase @Inject constructor(
    private val repository: IGetHotel,
    private val hotelDao: HotelDao
) {

    suspend fun getHotelsRemote(): Flow<Resource<Hotel>> = flow {
        try {

            emit(Resource.Loading())

            val hotels = repository.getHotel()
            hotelDao.insert(
                HotelEntity(
                    hotels.id,
                    hotels.name,
                    hotels.adress,
                    hotels.minimal_price,
                    hotels.price_for_it,
                    hotels.rating,
                    hotels.rating_name,
                    hotels.image_urls,
                    hotels.about_the_hotel,
                )
            )

            emit(Resource.Success(hotels))
        }catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }

    suspend fun getFromLocal():  Flow<Resource<Hotel>> = flow {
        try {

            emit(Resource.Loading())

                val localHotel = hotelDao.getAllHotels()

                val hotel  =  Hotel(
                    localHotel.id,
                    localHotel.name,
                    localHotel.adress,
                    localHotel.minimal_price,
                    localHotel.price_for_it,
                    localHotel.rating,
                    localHotel.rating_name,
                    localHotel.image_urls,
                    localHotel.about_the_hotel,
                )
                emit(Resource.Success(hotel))


        }catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}
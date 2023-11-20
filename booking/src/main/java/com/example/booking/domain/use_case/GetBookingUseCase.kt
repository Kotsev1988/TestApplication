package com.example.booking.domain.use_case

import com.example.api.api.models.Booking
import com.example.booking.data.room.BookingDao
import com.example.booking.data.room.BookingEntity
import com.example.booking.domain.IGetBooking
import com.example.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetBookingUseCase@Inject constructor(
    private val repository: IGetBooking,
    private val bookingDao: BookingDao
)  {

    suspend fun getBookingRemote(): Flow<Resource<Booking>> = flow {
        try {

            emit(Resource.Loading())

            val booking = repository.getBooking()
            bookingDao.insert(
                BookingEntity(
                    booking.arrival_country,
                    booking.departure,
                    booking.fuel_charge,
                    booking.horating,
                    booking.hotel_adress,
                    booking.hotel_name,
                    booking.id,
                    booking.number_of_nights,
                    booking.nutrition,
                    booking.rating_name,
                    booking.room,
                    booking.service_charge,
                    booking.tour_date_start,
                    booking.tour_date_stop,
                    booking.tour_price
                    )


            )

            emit(Resource.Success(booking))
        }catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }

    suspend fun getBookingLocal(): Flow<Resource<Booking>> = flow {
        try {

            emit(Resource.Loading())

            val booking = bookingDao.getAllBooking()

            val bookingEntity  =  Booking(
                booking.arrival_country,
                booking.departure,
                booking.fuel_charge,
                booking.horating,
                booking.hotel_adress,
                booking.hotel_name,
                booking.id,
                booking.number_of_nights,
                booking.nutrition,
                booking.rating_name,
                booking.room,
                booking.service_charge,
                booking.tour_date_start,
                booking.tour_date_stop,
                booking.tour_price
            )
            emit(Resource.Success(bookingEntity))


        }catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}
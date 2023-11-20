package com.example.number.domain.use_case

import com.example.api.api.models.Room
import com.example.api.api.models.Rooms
import com.example.number.data.room.RoomDao
import com.example.number.data.room.RoomEntity
import com.example.number.domain.IGetRooms
import com.example.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetHotelRoomUseCase @Inject constructor (
    private val repository: IGetRooms,
    private val hotelDao: RoomDao
){
    suspend fun getHotelRoomsRemote(): Flow<Resource<Rooms>> = flow {
        try {

            emit(Resource.Loading())

            val hotelRooms = repository.getRooms()
            val roomsEntity = hotelRooms.rooms.map {
                RoomEntity(
                    it.id,
                    it.image_urls,
                    it.name,
                    it.peculiarities,
                    it.price,
                    it.price_per
                )
            }
            hotelDao.insert(
                roomsEntity
            )

            emit(Resource.Success(hotelRooms))
        }catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }

    suspend fun getHotelRoomsLocal(): Flow<Resource<Rooms>> = flow {
        try {

            emit(Resource.Loading())

            val localHotelRooms = hotelDao.getAllHotels()
            val rooms = localHotelRooms.map {
                Room(
                    it.id,
                    it.image_urls,
                    it.name,
                    it.peculiarities,
                    it.price,
                    it.price_per
                )
            }

           val roomHotelList = Rooms(rooms)
            emit(Resource.Success(roomHotelList))
        }catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}
package com.challenge.fourthwall.commons.util

import com.challenge.fourthwall.commons.dto.MovieShowDTO
import com.challenge.fourthwall.commons.entity.Movie
import com.challenge.fourthwall.commons.entity.MovieShow
import com.challenge.fourthwall.commons.entity.Room
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

fun createMovieEntityTest() = Movie(
    id="tt0322259",name="movie test", isDeleted = false, lastUpdatedDate = LocalDateTime.now()
)
fun createRoomEntityTest() = Room(
    id= UUID.randomUUID(),name="room test",isDeleted = false, lastUpdatedDate = LocalDateTime.now(), capacity = 100
)

fun createMovieShow() = MovieShow(
    id=UUID.randomUUID(), movie= createMovieEntityTest(), room= createRoomEntityTest(), duration = 90, initialDateTime = LocalDateTime.now(),
    finalDateTime = LocalDateTime.now(), price = BigDecimal(5000)
)

fun createMovieShowDTO(movieId:String="tetsmovieid",roomId:UUID=UUID.randomUUID()) = MovieShowDTO(
    id=null, movieName = "asa", roomName = "test room", movieId = movieId, roomId = roomId,
    duration = 90, initialDateTime = LocalDateTime.now(), finalDateTime = LocalDateTime.now(), price = BigDecimal(5000)
)
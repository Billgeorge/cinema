package com.challenge.fourthwall.commons.dto

import com.challenge.fourthwall.commons.entity.Movie
import com.challenge.fourthwall.commons.entity.MovieShow
import com.challenge.fourthwall.commons.entity.Review
import com.challenge.fourthwall.commons.entity.Room
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

data class MovieShowDTO(
    val id: UUID?,
    val movieName: String,
    val roomName: String,
    val movieId: String,
    val roomId: UUID,
    val initialDateTime: LocalDateTime,
    val finalDateTime: LocalDateTime,
    val price: BigDecimal,
    val duration:Long
)

fun MovieShow.toDTO() = MovieShowDTO(
    id = id,
    movieName = movie.name, roomName = room.name,
    movieId = movie.id, roomId = room.id,
    initialDateTime = initialDateTime,
    finalDateTime = finalDateTime, price = price, duration = duration
)

fun MovieShowDTO.toEntity(movieIn: Movie, roomIn: Room) = MovieShow(
    id = id ?: UUID.randomUUID(),
    movie = movieIn,
    room = roomIn,
    initialDateTime = initialDateTime,
    finalDateTime = finalDateTime,
    price = price,
    duration = duration
)

data class ReviewDTO(
    val id:UUID?,
    val comment:String,
    val name:String,
    val movieId: String,
    val score:Int
)

fun ReviewDTO.toEntity(movie:Movie) = Review(
    id=id?:UUID.randomUUID(), lastUpdatedDate = LocalDateTime.now(),
    comment=comment,name=name, movie = movie, score = score
)

fun Review.toDTO()= ReviewDTO(
    id=id,comment=comment, name = name, movieId = movie.id,score=score
)
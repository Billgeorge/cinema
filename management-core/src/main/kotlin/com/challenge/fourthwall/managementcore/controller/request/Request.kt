package com.challenge.fourthwall.managementcore.controller.request

import com.challenge.fourthwall.commons.dto.MovieShowDTO
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*
import javax.validation.constraints.Min
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class CreateMovieShowRequest(
    @get:NotEmpty(message = "movieId can not be null")
    val movieId: String,
    @get:NotNull(message = "roomId can not be null")
    val roomId: UUID?,
    @get:NotNull(message = "initialLocalDateTime can not be null")
    val initialLocalDateTime: LocalDateTime?,
    @get:NotNull(message = "durationInMinutes can not be null")
    @get:Min(value = 1, message = "durationInMinutes must be greater than one")
    val durationInMinutes: Long?,
    @get:NotNull(message = "price can not be null")
    val price: BigDecimal?
)

fun CreateMovieShowRequest.toDTO(finalDateTime: LocalDateTime) = MovieShowDTO(
    movieId = movieId, roomId = roomId!!, movieName = "", roomName = "", finalDateTime = finalDateTime, id = null,
    initialDateTime = initialLocalDateTime!!, price = price!!, duration = durationInMinutes?:0L
)

data class ChangePriceMovieShowRequest(
    @get:NotNull(message = "moviShowId can not be null")
    val moviShowId: UUID?,
    @get:NotNull(message = "price can not be null")
    val price:BigDecimal?
)

data class ChangeDateMovieShowRequest(
    @get:NotNull(message = "moviShowId can not be null")
    val movieShowId: UUID?,
    val initialDateTime:LocalDateTime?,
    val duration:Long?
)
package com.challenge.fourthwall.managementcore.controller.response

import com.challenge.fourthwall.commons.entity.MovieShow
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

data class CreateMovieShowResponse(
    val id: UUID,
    val initialDateTime: LocalDateTime,
    val finalDateTime: LocalDateTime,
    val price: BigDecimal
)

fun MovieShow.toCreateResponse() = CreateMovieShowResponse(
    id=id, initialDateTime=initialDateTime,finalDateTime=finalDateTime,price=price
)
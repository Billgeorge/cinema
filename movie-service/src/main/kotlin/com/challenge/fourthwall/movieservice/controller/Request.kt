package com.challenge.fourthwall.movieservice.controller

import com.challenge.fourthwall.commons.dto.ReviewDTO
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class CreationReviewRequest(
    @get:NotBlank(message = "comment can not be null or blank")
    val comment:String?,
    @get:NotBlank(message = "name can not be null or blank")
    val name:String?,
    @get:NotBlank(message = "movieId can not be null or blank")
    val movieId: String?,
    @get:NotNull(message = "movieId can not be null or blank")
    @get:Min(value = 0L, message = "movieId must be from 0 to 5")
    @get:Max(value = 5L, message = "movieId must be from 0 to 5")
    val score:Int
)

fun CreationReviewRequest.toDTO()=ReviewDTO(
    comment = comment!!, name = name!!, movieId = movieId!!, score = score, id=null
)
package com.challenge.fourthwall.movieservice.controller.response

import com.challenge.fourthwall.commons.entity.Review
import java.util.*

data class CreateReviewResponse
    (
    val id: UUID,
    val movieName: String,
    val comment: String,
    val commentOwnerName:String,
    val score:Int
)

fun Review.toCreateReviewResponse()= CreateReviewResponse(
    id=id,
    movieName = movie.name,
    comment=comment,
    commentOwnerName = name,
    score= score
)

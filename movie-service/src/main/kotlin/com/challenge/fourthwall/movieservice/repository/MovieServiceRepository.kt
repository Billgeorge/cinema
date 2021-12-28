package com.challenge.fourthwall.movieservice.repository

import com.challenge.fourthwall.commons.dto.*
import com.challenge.fourthwall.commons.repository.MovieRepository
import com.challenge.fourthwall.commons.repository.MovieShowRepository
import com.challenge.fourthwall.commons.repository.ReviewRepository
import com.challenge.fourthwall.movieservice.controller.response.toCreateReviewResponse
import org.springframework.stereotype.Service

interface MovieServiceRepository {
    fun getAllActiveShows(): List<MovieShowDTO>
    fun save(reviewDTO: ReviewDTO): GenericResponse<*>
}

@Service
class MovieServiceRepositoryImpl(
    private val movieShowRepository: MovieShowRepository,
    private val movieRepository: MovieRepository,
    private val reviewRepository: ReviewRepository
) : MovieServiceRepository {
    override fun getAllActiveShows() = movieShowRepository.findAllByInitialDateTimeAfter().map { it.toDTO() }
    override fun save(reviewDTO: ReviewDTO): GenericResponse<*> {
        val optionalMovie = movieRepository.findById(reviewDTO.movieId)
        return if (optionalMovie.isEmpty) {
            GenericResponse.ErrorResponse("Movie does not exist")
        } else {
            val movie = optionalMovie.get()
            GenericResponse.SuccessResponse(reviewRepository.save(reviewDTO.toEntity(movie)).toCreateReviewResponse())
        }
    }
}
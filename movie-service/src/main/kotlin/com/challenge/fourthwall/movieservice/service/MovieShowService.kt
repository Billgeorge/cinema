package com.challenge.fourthwall.movieservice.service

import com.challenge.fourthwall.commons.dto.GenericResponse
import com.challenge.fourthwall.commons.dto.MovieShowDTO
import com.challenge.fourthwall.commons.dto.ReviewDTO
import com.challenge.fourthwall.movieservice.client.OpenMovieAPIClient
import com.challenge.fourthwall.movieservice.repository.MovieServiceRepository

import org.springframework.stereotype.Service

interface MovieShowService {
    fun getActiveMovieShows():List<MovieShowDTO>
    fun getMovieInfo(movieId:String): GenericResponse<*>
    fun createReview(review: ReviewDTO): GenericResponse<*>
}

@Service
class MovieShowServiceImpl(private val movieServiceRepository: MovieServiceRepository, private val openMovieAPIClient: OpenMovieAPIClient):
    MovieShowService {
    override fun getActiveMovieShows() = movieServiceRepository.getAllActiveShows()
    override fun getMovieInfo(movieId:String): GenericResponse<*> = openMovieAPIClient.getMovieInformation(movieId)
    override fun createReview(review: ReviewDTO)= movieServiceRepository.save(review)

}
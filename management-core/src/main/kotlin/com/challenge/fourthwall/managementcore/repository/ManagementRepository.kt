package com.challenge.fourthwall.managementcore.repository

import com.challenge.fourthwall.commons.dto.GenericResponse
import com.challenge.fourthwall.commons.dto.MovieShowDTO
import com.challenge.fourthwall.commons.dto.toEntity
import com.challenge.fourthwall.managementcore.controller.request.ChangeDateMovieShowRequest
import com.challenge.fourthwall.managementcore.controller.request.ChangePriceMovieShowRequest
import com.challenge.fourthwall.commons.repository.MovieRepository
import com.challenge.fourthwall.commons.repository.MovieShowRepository
import com.challenge.fourthwall.commons.repository.RoomRepository
import com.challenge.fourthwall.managementcore.controller.response.toCreateResponse
import org.springframework.stereotype.Service

interface ManagementRepository {
    fun createMovieShow(movieShowDTO: MovieShowDTO): GenericResponse<*>
    fun changePrice(changePriceMovieShowRequest: ChangePriceMovieShowRequest): GenericResponse<*>
    fun changeDateAndDuration(changeDateMovieShowRequest: ChangeDateMovieShowRequest): GenericResponse<*>
}

@Service
class ManagementRepositoryImpl(
    private val movieShowRepository: MovieShowRepository,
    private val movieRepository: MovieRepository, private val roomRepository: RoomRepository
) :
    ManagementRepository {

    override fun createMovieShow(movieShowDTO: MovieShowDTO): GenericResponse<*> {
        val movieOptional = movieRepository.findById(movieShowDTO.movieId)
        if (movieOptional.isEmpty || movieOptional.get().isDeleted) {
            return GenericResponse.ErrorResponse("movie does not exist")
        }
        val movie = movieOptional.get()
        val roomOptional = roomRepository.findById(movieShowDTO.roomId)
        if (roomOptional.isEmpty || roomOptional.get().isDeleted) {
            return GenericResponse.ErrorResponse("room does not exist")
        }
        return GenericResponse.SuccessResponse(
            movieShowRepository
                .save(movieShowDTO.toEntity(movie, roomOptional.get())).toCreateResponse()
        )
    }

    override fun changePrice(changePriceMovieShowRequest: ChangePriceMovieShowRequest): GenericResponse<*> {
        val existingMovieShow = movieShowRepository.findById(changePriceMovieShowRequest.moviShowId!!)
        if (existingMovieShow.isEmpty) {
            return GenericResponse.ErrorResponse("movie show does not exist")
        }
        val movieShow = existingMovieShow.get()
        movieShow.price = changePriceMovieShowRequest.price!!
        return GenericResponse.SuccessResponse(
            movieShowRepository.save(movieShow).toCreateResponse()
        )
    }

    override fun changeDateAndDuration(changeDateMovieShowRequest: ChangeDateMovieShowRequest): GenericResponse<*> {
        val existingMovieShow = movieShowRepository.findById(changeDateMovieShowRequest.movieShowId!!)
        if (existingMovieShow.isEmpty) {
            return GenericResponse.ErrorResponse("movie show does not exist")
        }
        val movieShow = existingMovieShow.get()
        changeDateMovieShowRequest.initialDateTime?.let {
            movieShow.initialDateTime = it
        }
        var lastDuration =
            if (changeDateMovieShowRequest.duration != null && changeDateMovieShowRequest.duration > 0L) {
                changeDateMovieShowRequest.duration
            } else {
                movieShow.duration
            }
        movieShow.duration = lastDuration
        movieShow.finalDateTime = movieShow.initialDateTime.plusMinutes(lastDuration)
        return GenericResponse.SuccessResponse(
            movieShowRepository.save(movieShow).toCreateResponse()
        )
    }
}
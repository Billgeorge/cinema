package com.challenge.fourthwall.movieservice.controller

import com.challenge.fourthwall.commons.dto.GenericResponse
import com.challenge.fourthwall.movieservice.client.OpenMovieResponse
import com.challenge.fourthwall.movieservice.controller.response.CreateReviewResponse
import com.challenge.fourthwall.movieservice.service.MovieShowService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("/show")
class MovieShowController(private val movieShowService: MovieShowService) {

    @GetMapping
    fun getActiveShows(): ResponseEntity<*> {
        val movieShows = movieShowService.getActiveMovieShows()
        return if (movieShows.isEmpty()) {
            ResponseEntity.notFound().build<String>()
        } else {
            ResponseEntity.ok(movieShows)
        }
    }

    @GetMapping("/info/{movieId}")
    fun getMovieInfo(@PathVariable movieId: String): ResponseEntity<*> {
        return when (val serviceResponse = movieShowService.getMovieInfo(movieId)) {
            is GenericResponse.SuccessResponse -> ResponseEntity.ok()
                .body(serviceResponse.objP as OpenMovieResponse)
            is GenericResponse.ErrorResponse -> ResponseEntity.internalServerError().body(serviceResponse.objP)
        }
    }

    @PostMapping("/review")
    fun getMovieInfo(@Valid @RequestBody request: CreationReviewRequest): ResponseEntity<*> {
        return when (val serviceResponse = movieShowService.createReview(request.toDTO())) {
            is GenericResponse.SuccessResponse -> ResponseEntity.ok()
                .body(serviceResponse.objP as CreateReviewResponse)
            is GenericResponse.ErrorResponse -> ResponseEntity.internalServerError().body(serviceResponse.objP)
        }
    }

    @GetMapping("/test")
    fun test(): String {
        return "ok"
    }
}
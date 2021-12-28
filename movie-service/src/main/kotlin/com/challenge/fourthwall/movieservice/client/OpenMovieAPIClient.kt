package com.challenge.fourthwall.movieservice.client

import com.challenge.fourthwall.commons.dto.GenericResponse
import com.challenge.fourthwall.movieservice.client.OpenMovieResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

interface OpenMovieAPIClient {
    fun getMovieInformation(movieId: String): GenericResponse<*>
}

@Service
class OpenMovieClientImpl : OpenMovieAPIClient {

    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    @Value("\${open.movie.api.key}")
    lateinit var apiKey: String

    @Value("\${open.movie.url}")
    lateinit var apiUrl: String

    private val restTemplate = RestTemplate()

    override fun getMovieInformation(movieId: String): com.challenge.fourthwall.commons.dto.GenericResponse<*> {
        return try {
            val urlGet = "$apiUrl?apikey=$apiKey&i=$movieId"
            val response = restTemplate.getForEntity(urlGet, OpenMovieResponse::class.java)
            if (response.statusCode == HttpStatus.OK) {
                val responseBody = response.body as OpenMovieResponse
                com.challenge.fourthwall.commons.dto.GenericResponse.SuccessResponse(responseBody)
            } else {
                com.challenge.fourthwall.commons.dto.GenericResponse.ErrorResponse("Error calling API")
            }
        } catch (e: Exception) {
            log.error("Error calling open movie API",e)
            com.challenge.fourthwall.commons.dto.GenericResponse.ErrorResponse("Error calling API")
        }
    }

}
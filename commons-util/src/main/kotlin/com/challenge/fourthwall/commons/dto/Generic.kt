package com.challenge.fourthwall.commons.dto

sealed class GenericResponse<T>(val objP: T) {
    data class SuccessResponse<T>(val obj: T) : com.challenge.fourthwall.commons.dto.GenericResponse<T>(obj)
    data class ErrorResponse(val message: String) : com.challenge.fourthwall.commons.dto.GenericResponse<String>(message)
}
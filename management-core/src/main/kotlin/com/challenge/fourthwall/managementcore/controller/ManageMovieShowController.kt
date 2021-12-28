package com.challenge.fourthwall.managementcore.controller

import com.challenge.fourthwall.managementcore.business.ManagementService
import com.challenge.fourthwall.managementcore.controller.request.ChangeDateMovieShowRequest
import com.challenge.fourthwall.managementcore.controller.request.ChangePriceMovieShowRequest
import com.challenge.fourthwall.managementcore.controller.request.CreateMovieShowRequest
import com.challenge.fourthwall.managementcore.controller.response.CreateMovieShowResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("v1/manage")
class ManageMovieShowController(private val managementService: ManagementService) {

    @GetMapping()
    fun get():String{
        return "ok"
    }

    @PostMapping("/movieshow")
    fun createMovieShow(@Valid @RequestBody request: CreateMovieShowRequest): ResponseEntity<*> {
        return when (val serviceResponse = managementService.createMovieShow(request)) {
            is com.challenge.fourthwall.commons.dto.GenericResponse.SuccessResponse -> ResponseEntity.ok()
                .body(serviceResponse.objP as CreateMovieShowResponse)
            is com.challenge.fourthwall.commons.dto.GenericResponse.ErrorResponse -> ResponseEntity.internalServerError().body(serviceResponse.objP)
        }
    }

    @PatchMapping("movieshow/price")
    fun changePrice(@Valid @RequestBody request: ChangePriceMovieShowRequest): ResponseEntity<*> {
        return when (val serviceResponse = managementService.changePrice(request)) {
            is com.challenge.fourthwall.commons.dto.GenericResponse.SuccessResponse -> ResponseEntity.ok()
                .body(serviceResponse.objP as CreateMovieShowResponse)
            is com.challenge.fourthwall.commons.dto.GenericResponse.ErrorResponse -> ResponseEntity.internalServerError().body(serviceResponse.objP)
        }
    }

    @PatchMapping("movieshow/date")
    fun changeDate(@Valid @RequestBody request: ChangeDateMovieShowRequest): ResponseEntity<*> {
        return when (val serviceResponse = managementService.changeDateAndDuration(request)) {
            is com.challenge.fourthwall.commons.dto.GenericResponse.SuccessResponse -> ResponseEntity.ok()
                .body(serviceResponse.objP as CreateMovieShowResponse)
            is com.challenge.fourthwall.commons.dto.GenericResponse.ErrorResponse -> ResponseEntity.internalServerError().body(serviceResponse.objP)
        }
    }
}
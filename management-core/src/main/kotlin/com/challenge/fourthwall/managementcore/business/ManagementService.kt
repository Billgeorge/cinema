package com.challenge.fourthwall.managementcore.business

import com.challenge.fourthwall.managementcore.controller.request.ChangeDateMovieShowRequest
import com.challenge.fourthwall.managementcore.controller.request.ChangePriceMovieShowRequest
import com.challenge.fourthwall.managementcore.controller.request.CreateMovieShowRequest
import com.challenge.fourthwall.managementcore.controller.request.toDTO
import com.challenge.fourthwall.managementcore.repository.ManagementRepository
import org.springframework.stereotype.Service

interface ManagementService {
    fun createMovieShow(request: CreateMovieShowRequest): com.challenge.fourthwall.commons.dto.GenericResponse<*>
    fun changePrice(changePriceMovieShowRequest: ChangePriceMovieShowRequest): com.challenge.fourthwall.commons.dto.GenericResponse<*>
    fun changeDateAndDuration(changeDateMovieShowRequest: ChangeDateMovieShowRequest): com.challenge.fourthwall.commons.dto.GenericResponse<*>
}

@Service
class ManagementServiceImpl(private val managementRepository: ManagementRepository) : ManagementService {
    override fun createMovieShow(request: CreateMovieShowRequest): com.challenge.fourthwall.commons.dto.GenericResponse<*> {
        if(request.durationInMinutes==0L){
            return com.challenge.fourthwall.commons.dto.GenericResponse.ErrorResponse("duration is zero")
        }
        val finalDateTime = request.initialLocalDateTime!!.plusMinutes(request.durationInMinutes!!)
        return managementRepository.createMovieShow(request.toDTO(finalDateTime))
    }

    override fun changePrice(changePriceMovieShowRequest: ChangePriceMovieShowRequest) = managementRepository
        .changePrice(changePriceMovieShowRequest)

    override fun changeDateAndDuration(changeDateMovieShowRequest: ChangeDateMovieShowRequest): com.challenge.fourthwall.commons.dto.GenericResponse<*> {
        if(changeDateMovieShowRequest.duration==null && changeDateMovieShowRequest.initialDateTime==null){
            return com.challenge.fourthwall.commons.dto.GenericResponse.ErrorResponse("Send duration or initialDate to update")
        }
        return managementRepository.changeDateAndDuration(changeDateMovieShowRequest)
    }

}
package com.challenge.fourthwall.commons.repository

import com.challenge.fourthwall.commons.entity.MovieShow
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.util.*

@Repository
interface MovieShowRepository : JpaRepository<MovieShow, UUID>{

    fun findAllByInitialDateTimeAfter(currentDateTime:LocalDateTime=LocalDateTime.now()):List<MovieShow>
}
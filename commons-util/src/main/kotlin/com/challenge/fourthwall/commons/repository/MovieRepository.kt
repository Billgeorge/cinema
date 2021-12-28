package com.challenge.fourthwall.commons.repository

import com.challenge.fourthwall.commons.entity.Movie
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface MovieRepository : JpaRepository<Movie, String>
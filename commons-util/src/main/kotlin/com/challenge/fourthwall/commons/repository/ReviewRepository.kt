package com.challenge.fourthwall.commons.repository

import com.challenge.fourthwall.commons.entity.Review
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ReviewRepository:JpaRepository<Review,UUID>
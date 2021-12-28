package com.challenge.fourthwall.commons.repository

import com.challenge.fourthwall.commons.entity.Room
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface RoomRepository:JpaRepository<Room, UUID>
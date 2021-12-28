package com.challenge.fourthwall.commons.entity

import lombok.Getter
import lombok.Setter
import java.time.LocalDateTime
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Getter
@Setter
@Table(name = "room")
class Room(
    @Id val id: UUID,
    @Column(name = "name") var name: String,
    @Column(name = "is_deleted") var isDeleted: Boolean,
    @Column(name = "last_updated_date") var lastUpdatedDate: LocalDateTime,
    @Column(name = "capacity") var capacity: Int
)
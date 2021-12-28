package com.challenge.fourthwall.commons.entity

import lombok.Getter
import lombok.Setter
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Getter @Setter
@Table(name = "MOVIE")
class Movie(
    @Id val id: String,
    @Column(name = "name") var name: String,
    @Column(name = "is_deleted") var isDeleted: Boolean,
    @Column(name = "last_updated_date") var lastUpdatedDate: LocalDateTime
)
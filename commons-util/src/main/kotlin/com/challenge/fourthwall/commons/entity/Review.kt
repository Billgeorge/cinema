package com.challenge.fourthwall.commons.entity

import lombok.Getter
import lombok.Setter
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Getter
@Setter
@Table(name = "review")
class Review(
    @Id val id: UUID,
    @Column(name = "lastUpdatedDate") var lastUpdatedDate: LocalDateTime,
    @Column(name = "name") var name: String,
    @OneToOne(cascade = [CascadeType.ALL]) @JoinColumn(name = "movie", referencedColumnName = "id")
    var movie: Movie,
    @Column(name = "comment") var comment: String,
    @Column(name = "score") var score: Int
)
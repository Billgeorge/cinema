package com.challenge.fourthwall.commons.entity

import lombok.Getter
import lombok.Setter
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Getter
@Setter
@Table(name = "movie_show")
class MovieShow(
    @Id val id: UUID,
    @OneToOne(cascade = [CascadeType.ALL])  @JoinColumn(name = "room", referencedColumnName = "id")
    var room: Room,
    @OneToOne(cascade = [CascadeType.ALL])  @JoinColumn(name = "movie", referencedColumnName = "id")
    var movie: Movie,
    @Column(name = "initial_date_time") var initialDateTime: LocalDateTime,
    @Column(name = "final_date_time") var finalDateTime: LocalDateTime,
    @Column(name = "duration") var duration: Long,
    @Column(name = "price") var price: BigDecimal
)
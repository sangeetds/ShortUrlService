package `in`.sangeet.multiDB.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity(name = "short_url")
data class ShortUrl(

    @Id val key: String,

    @Column(name = "long_url")
    val longUrl: String,

    @Column(name = "time_of_creation")
    private val timeOfCreation: LocalDateTime,
)

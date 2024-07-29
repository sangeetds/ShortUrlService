package `in`.sangeet.multiDB.repository

import `in`.sangeet.multiDB.model.ShortUrl
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ShortUrlRepository: JpaRepository<ShortUrl, String> {

    fun findByKey(shortUrl: String): ShortUrl?


}
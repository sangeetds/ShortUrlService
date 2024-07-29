package `in`.sangeet.multiDB.service.impl

import `in`.sangeet.multiDB.dto.LongUrlDTO
import org.springframework.stereotype.Service


interface UrlShortService {

    fun createShortUrl(longUrlDTO: LongUrlDTO): String

    fun getLongUrl(shortUrl: String): String
}
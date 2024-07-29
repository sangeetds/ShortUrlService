package `in`.sangeet.multiDB.service.impl

import `in`.sangeet.multiDB.dto.LongUrlDTO
import `in`.sangeet.multiDB.exceptions.UrlKeyNotPresentException
import `in`.sangeet.multiDB.model.ShortUrl
import `in`.sangeet.multiDB.repository.ShortUrlRepository
import org.springframework.stereotype.Service
import org.springframework.util.DigestUtils
import java.time.LocalDateTime

@Service
class UrlShortServiceImpl(private val shortUrlRepository: ShortUrlRepository) : UrlShortService {

    override fun createShortUrl(longUrlDTO: LongUrlDTO): String {
        validateUrl(longUrlDTO.longUrl)
        val url = createNewUrl(longUrlDTO.longUrl)
        val shortUrl = shortUrlRepository.save(
            ShortUrl(
                key = url,
                longUrl = longUrlDTO.longUrl,
                timeOfCreation = LocalDateTime.now()
            )
        )

        return shortUrl.key
    }

    override fun getLongUrl(shortUrl: String): String {
        val url = shortUrlRepository.findByKey(shortUrl)

        return url?.longUrl ?: throw UrlKeyNotPresentException("The provided key doesn't match to any linked saved");
    }


    private fun createNewUrl(longUrl: String): String = DigestUtils.md5DigestAsHex(longUrl.toByteArray())

    private fun validateUrl(longUrl: String) {

    }
}
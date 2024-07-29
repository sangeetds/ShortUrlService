package `in`.sangeet.multiDB.controller

import `in`.sangeet.multiDB.dto.LongUrlDTO
import `in`.sangeet.multiDB.exceptions.UrlKeyNotPresentException
import `in`.sangeet.multiDB.exceptions.UrlValidationException
import `in`.sangeet.multiDB.service.impl.UrlShortService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import java.net.URI

@Controller
class ShortUrlController(val urlShortService: UrlShortService) {

    @PostMapping(value = ["/shortUrl"])
    fun createShortUrl(@RequestBody longUrlDTO: LongUrlDTO): ResponseEntity<String> {
        try {
            val shortUrl = urlShortService.createShortUrl(longUrlDTO)
            return ResponseEntity.created(URI.create(shortUrl)).build()
        } catch (validationEx: UrlValidationException) {
            return ResponseEntity.badRequest().body(validationEx.message)
        } catch (ex: RuntimeException) {
            return ResponseEntity.internalServerError().build()
        }
    }

    @GetMapping(value = ["/{urlKey}"])
    fun getOriginalUrl(@PathVariable urlKey: String): ResponseEntity<Any> {
        try {
            val longUrl = urlShortService.getLongUrl(urlKey)
            return ResponseEntity.status(HttpStatus.PERMANENT_REDIRECT).body(longUrl)
        } catch (urlNotFoundEx: UrlKeyNotPresentException) {
            return ResponseEntity.badRequest().body(urlNotFoundEx.message)
        } catch (ex: RuntimeException) {
            return ResponseEntity.internalServerError().build()
        }
    }

}
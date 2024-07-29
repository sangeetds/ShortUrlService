package `in`.sangeet

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CRUDApplication

fun main(args: Array<String>) {
	runApplication<CRUDApplication>(*args)
}

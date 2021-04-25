package com.julyseven.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication(scanBasePackages = ["com.julyseven"])
@EnableJpaRepositories("com.julyseven.common.repository")
@EntityScan("com.julyseven.common.entity")
class JulySevenApiApplication

fun main(args: Array<String>) {
	runApplication<JulySevenApiApplication>(*args)
}

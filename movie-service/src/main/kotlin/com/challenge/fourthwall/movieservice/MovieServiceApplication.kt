package com.challenge.fourthwall.movieservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories


@SpringBootApplication
@EntityScan(basePackages = ["com.challenge.fourthwall.commons.entity"])
@EnableJpaRepositories(basePackages = ["com.challenge.fourthwall.commons.repository"])
@ComponentScan(basePackages = ["com.challenge.fourthwall","com.challenge.fourthwall.commons.repository"])
class MovieServiceApplication

fun main(args: Array<String>) {
    runApplication<MovieServiceApplication>(*args)
}
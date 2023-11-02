package com.maro.maroassetsweb

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication(scanBasePackages = ["com.maro"])
@EnableJpaRepositories(basePackages = ["com.maro"])
@EntityScan(basePackages = ["com.maro"])
@EnableJpaAuditing
class MaroAssetsWebApplication

fun main(args: Array<String>) {
    runApplication<MaroAssetsWebApplication>(*args)
}
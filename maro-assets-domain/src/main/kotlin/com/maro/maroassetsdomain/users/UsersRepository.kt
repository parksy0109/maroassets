package com.maro.maroassetsdomain.users

import org.springframework.data.jpa.repository.JpaRepository

interface UsersRepository: JpaRepository<Users, Long> {
    fun findByName(name: String): Users?
}
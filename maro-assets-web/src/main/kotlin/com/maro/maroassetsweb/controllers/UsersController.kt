package com.maro.maroassetsweb.controllers

import com.maro.maroassetsdomain.users.UsersService
import com.maro.maroassetsweb.dto.LoginDto
import com.maro.maroassetsweb.dto.SingUpDto
import org.springframework.http.MediaType
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UsersController(
    private val usersService: UsersService,
    private val passwordEncoder: PasswordEncoder
) {

    @PostMapping(
        value = ["/api/v1/users"],
        produces = [MediaType.APPLICATION_JSON_VALUE],
        consumes = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun signUpForUser(@RequestBody singUpDto: SingUpDto) {
        return usersService.create(singUpDto.name, passwordEncoder.encode(singUpDto.password), singUpDto.email)
    }

    @PostMapping(
        value = ["/api/v1/users/auth"],
        produces = [MediaType.APPLICATION_JSON_VALUE],
        consumes = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun login(@RequestBody loginDto: LoginDto): Boolean {
        return passwordEncoder.matches(loginDto.password, usersService.login(loginDto.name))
    }

}
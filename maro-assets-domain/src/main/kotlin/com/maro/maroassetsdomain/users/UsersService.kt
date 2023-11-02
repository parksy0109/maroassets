package com.maro.maroassetsdomain.users

interface UsersService {

    fun create(username: String, userPassword: String, email: String)

    fun getAllUsers(): List<Users>

    fun delete(username: String, userPassword: String)

    fun update(username: String, userPassword: String)

    fun login(username: String): String
}
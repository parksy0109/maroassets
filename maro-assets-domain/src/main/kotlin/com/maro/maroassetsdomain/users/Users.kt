package com.maro.maroassetsdomain.users

import javax.persistence.*

@Entity
@Table(name = "SAMPLE_ASSETS_USERS")
data class Users(
    @Column(name = "NAME",unique = true) val name: String,
    @Column(name = "PASSWORD") val password: String,
    @Column(name = "EMAIL") val email: String
) {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

}
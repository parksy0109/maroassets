package com.maro.maroassetsdomain.users

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository

@Repository
class UsersRepositoryImpl(
    private val jpaQueryFactory: JPAQueryFactory
): QuerydslRepositorySupport(Users::class.java), UsersCustomRepository {
    override fun updateByName() {

    }
}
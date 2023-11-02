package com.maro.maroassetsdomain.users

import com.maro.maroassetsdomain.exception.SampleAssetsDomainRuntimeException
import org.springframework.stereotype.Service

@Service
class UsersServiceImpl(
    private val usersRepository: UsersRepository
) : UsersService {
    override fun create(username: String, userPassword: String, email: String) {
        try {
            usersRepository.save(Users(username, userPassword, email))
        } catch (e: Exception) {
            throw SampleAssetsDomainRuntimeException("USI-CR-01", e.message ?: "회원가입 중 오류가 발생하였습니다.")
        }
    }

    override fun getAllUsers(): List<Users> {
        return usersRepository.findAll()
    }

    override fun delete(username: String, userPassword: String) {

    }

    override fun update(username: String, userPassword: String) {
    }

    override fun login(username: String): String {
        return usersRepository.findByName(username)?.password ?: run {
            throw SampleAssetsDomainRuntimeException("USI-LGI-01", "존재하지 않는 아이디 입니다.")
        }
    }
}
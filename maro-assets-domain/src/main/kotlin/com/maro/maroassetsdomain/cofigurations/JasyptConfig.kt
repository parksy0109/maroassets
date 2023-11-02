package com.maro.maroassetsdomain.cofigurations

import org.jasypt.encryption.StringEncryptor
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JasyptConfig {

    companion object {
        const val ENCRYPT_KEY = "IBJasyptEncryptKey"
    }

    @Bean("jasyptStringEncryptor")
    fun stringEncryptor(): StringEncryptor {
        val encryptor = PooledPBEStringEncryptor()
        val config = SimpleStringPBEConfig()
        config.apply {
            password = ENCRYPT_KEY
            algorithm = "PBEWithMD5AndDES"
            setKeyObtentionIterations("1000")
            setPoolSize("1")
            setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator")
            stringOutputType = "base64"
        }
        encryptor.setConfig(config)

        return encryptor
    }
}

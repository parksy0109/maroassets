package com.maro.maroassetsdomain.exception

import java.lang.RuntimeException

class SampleAssetsDomainRuntimeException(
    private val errorCode: String,
    private val errorMessage: String
) : RuntimeException("[$errorCode] $errorMessage ")
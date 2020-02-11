package ru.fors.auth.data.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import ru.fors.auth.api.domain.dto.Credentials
import ru.fors.auth.api.domain.dto.UserInfo
import ru.fors.auth.api.domain.dto.TokenResponse
import ru.fors.auth.api.domain.usecase.GetCallingUserSystemRoleUseCase
import ru.fors.auth.api.domain.usecase.SignInUseCase
import ru.fors.auth.api.domain.usecase.SignUpUseCase
import ru.fors.auth.data.utils.mapThrowable
import ru.fors.entity.auth.SystemUserRole

@RestController
open class AuthController(
        private val signInUseCase: SignInUseCase,
        private val signUpUseCase: SignUpUseCase,
        private val getCallingUserSystemRoleUseCase: GetCallingUserSystemRoleUseCase
) {

    @PostMapping("/login")
    fun login(@RequestBody credentials: Credentials): TokenResponse {
        return signInUseCase.execute(credentials)
    }

    @PostMapping("/signup/admin")
    fun registerAdmin(@RequestBody credentials: Credentials) {
        signUpUseCase.runCatching { execute(credentials, SystemUserRole.ADMIN) }
                .onFailure(::mapThrowable)
    }

    @PostMapping("/signup")
    fun registerUser(@RequestBody credentials: Credentials) {
        signUpUseCase.runCatching { execute(credentials, SystemUserRole.USER) }
                .onFailure(::mapThrowable)
    }

    @GetMapping("/auth/info")
    fun getSystemUserRole(): UserInfo {
        return getCallingUserSystemRoleUseCase.runCatching { execute() }
                .map { UserInfo(it.user.username, it.role) }
                .onFailure(::mapThrowable)
                .getOrThrow()
    }
}
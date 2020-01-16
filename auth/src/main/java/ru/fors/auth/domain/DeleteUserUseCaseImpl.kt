package ru.fors.auth.domain

import org.springframework.stereotype.Component
import ru.fors.auth.api.domain.DeleteUserUseCase
import ru.fors.auth.data.UserRepo
import ru.fors.entity.auth.User

@Component
class DeleteUserUseCaseImpl(
        private val userRepo: UserRepo
) : DeleteUserUseCase {
    override fun execute(user: User) {
        userRepo.delete(user)
    }
}
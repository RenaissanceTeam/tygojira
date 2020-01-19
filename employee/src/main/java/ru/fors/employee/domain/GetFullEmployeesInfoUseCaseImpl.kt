package ru.fors.employee.domain

import org.springframework.stereotype.Component
import ru.fors.employee.api.domain.GetFullEmployeesInfoUseCase
import ru.fors.employee.api.domain.dto.FullEmployeeInfoDto
import ru.fors.employee.data.repo.EmployeeRepo

@Component
class GetFullEmployeesInfoUseCaseImpl(
        private val employeeRepo: EmployeeRepo
) : GetFullEmployeesInfoUseCase {
    override fun execute(): List<FullEmployeeInfoDto> {
        return employeeRepo.findAll().map {
            FullEmployeeInfoDto(
                    id = it.id,
                    name = it.name,
                    position = it.position
            )
        }
    }
}
package ru.fors.workload.request.data.repo

import org.springframework.data.jpa.repository.JpaRepository
import ru.fors.entity.employee.Employee
import ru.fors.entity.workload.request.WorkloadRequest

interface WorkloadRequestRepo : JpaRepository<WorkloadRequest, Long> {
    fun findByInitiator(initiator: Employee): List<WorkloadRequest>
}
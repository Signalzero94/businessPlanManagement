package tmax.businessPlanManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tmax.businessPlanManagement.domain.Employee;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    Employee save(Employee employee);
    Optional<Employee> findByName(String name);
    List<Employee> findByDepartment(String department);
}

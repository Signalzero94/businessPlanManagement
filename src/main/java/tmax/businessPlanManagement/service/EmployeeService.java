package tmax.businessPlanManagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tmax.businessPlanManagement.domain.Employee;
import tmax.businessPlanManagement.repository.EmployeeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    public Employee findByName(String name) {
        return employeeRepository.findByName(name).get();
    }

    public List<Employee> findByDepartment(String department) {
        return employeeRepository.findByDepartment(department);
    }
}

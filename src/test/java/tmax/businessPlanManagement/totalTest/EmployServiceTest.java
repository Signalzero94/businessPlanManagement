package tmax.businessPlanManagement.totalTest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import tmax.businessPlanManagement.domain.Employee;
import tmax.businessPlanManagement.repository.EmployeeRepository;
import tmax.businessPlanManagement.service.EmployeeService;

@Transactional
@SpringBootTest
public class EmployServiceTest {

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EmployeeService employeeService;

    @Test
    void 인사정보테스트() {
        Employee employee = new Employee();
        employee.setName("호영");
        employeeService.save(employee);

        Assertions.assertThat(employee).isEqualTo(employeeService.findByName("호영"));
    }
}
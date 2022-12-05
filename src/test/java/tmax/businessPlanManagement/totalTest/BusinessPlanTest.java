package tmax.businessPlanManagement.totalTest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import tmax.businessPlanManagement.domain.*;
import tmax.businessPlanManagement.repository.ApprovalRepository;
import tmax.businessPlanManagement.repository.BusinessPlanRepository;
import tmax.businessPlanManagement.repository.EmployeeRepository;
import tmax.businessPlanManagement.repository.ProductRepository;
import tmax.businessPlanManagement.service.BusinessPlanService;
import tmax.businessPlanManagement.service.EmployeeService;

import java.util.List;


@SpringBootTest
@Transactional
class BusinessPlanTest {
    @Autowired
    ApprovalRepository approvalRepository;
    @Autowired
    BusinessPlanRepository businessPlanRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    BusinessPlanService businessPlanService;
    @Autowired
    EmployeeService employeeService;

    @Commit
    @Test
    void BusinessPlanServiceTest() {
        //인사정보 등록
        Employee employee = new Employee();
        employee.setName("호영");
        Employee employee1 = new Employee();
        employee1.setName("팀장");
        employeeService.save(employee);
        employeeService.save(employee1);

        //제품정보 등록
        Product product = new Product();
        product.setPart("superApp");
        productRepository.save(product);

        //결재정보 등록
        Approval approval = new Approval();
        approval.setApprovalType("합의");
        approval.setEmployee(employee1);
        Approval approval1 = new Approval();
        approval.setApprovalType("결재");
        approval.setEmployee(employee1);
        approvalRepository.save(approval1);
        approvalRepository.save(approval);

        //사업계획 정보 등록
        BusinessPlan businessPlan = new BusinessPlan();
        businessPlan.setApprovalList(approvalRepository.findAll());
        businessPlan.setPlanYear(2022L);
        businessPlan.setStatus(SubmitStatus.UNCOMPLETED);
        businessPlan.setProduct(product);
        businessPlan.setPrice(10000L);
        businessPlan.setEmployee(employee);
        Long saveId = businessPlanService.save(businessPlan);

        Assertions.assertThat("호영").isEqualTo(businessPlanService.findById(saveId).getEmployee().getName());
    }

    @Test
    @Commit
    void findBySomethingTest() {
        Employee employee = new Employee();
        employee.setName("호영");
        Employee employee1 = new Employee();
        employee1.setName("팀장");
        Employee employee2 = new Employee();
        employee2.setName("실장");
        employeeService.save(employee);
        employeeService.save(employee1);
        employeeService.save(employee2);

        //제품정보 등록
        Product product = new Product();
        product.setPart("superApp");
        productRepository.save(product);

        BusinessPlan businessPlan = new BusinessPlan();

        //결재정보 등록
        Approval approval = new Approval();
        approval.setApprovalType("합의");
        approval.setEmployee(employee1);
        approval.setBusinessPlan(businessPlan);

        Approval approval1 = new Approval();
        approval1.setApprovalType("결재");
        approval1.setEmployee(employee2);
        approval1.setBusinessPlan(businessPlan);


        approvalRepository.save(approval);
        approvalRepository.save(approval1);

        //사업계획 정보 등록
        businessPlan.setApprovalList(approvalRepository.findAll());
        businessPlan.setPlanYear(2022L);
        businessPlan.setStatus(SubmitStatus.UNCOMPLETED);
        businessPlan.setProduct(product);
        businessPlan.setPrice(10000L);
        businessPlan.setEmployee(employee);
        Long saveId = businessPlanService.save(businessPlan);


        //사업년도로 찾기
        List<BusinessPlan> businessPlans = businessPlanService.findByPlanYear(2022l);
        System.out.println("businessPlans 수량 = " + businessPlans.size());
        Assertions.assertThat(businessPlan).isEqualTo(businessPlans.get(0));
    }

    @Commit
    @Test
    void findByProductPart() {
        Employee employee = new Employee();
        employee.setName("호영");
        Employee employee1 = new Employee();
        employee1.setName("팀장");
        Employee employee2 = new Employee();
        employee2.setName("실장");
        employeeService.save(employee);
        employeeService.save(employee1);
        employeeService.save(employee2);

        //제품정보 등록
        Product product = new Product();
        product.setPart("superApp");
        productRepository.save(product);

        //사업계획 생성
        BusinessPlan businessPlan = new BusinessPlan();

        //결재정보 등록
        Approval approval = new Approval();
        approval.setApprovalType("합의");
        approval.setEmployee(employee1);
        approval.setBusinessPlan(businessPlan);

        Approval approval1 = new Approval();
        approval1.setApprovalType("결재");
        approval1.setEmployee(employee2);
        approval1.setBusinessPlan(businessPlan);

        approvalRepository.save(approval);
        approvalRepository.save(approval1);

        //사업계획 정보 등록
        businessPlan.setApprovalList(approvalRepository.findByBusinessPlan(businessPlan));
        businessPlan.setPlanYear(2022L);
        businessPlan.setStatus(SubmitStatus.UNCOMPLETED);
        businessPlan.setProduct(product);
        businessPlan.setPrice(10000L);
        businessPlan.setEmployee(employee);
        Long saveId = businessPlanService.save(businessPlan);


        List<BusinessPlan> businessPlans = businessPlanRepository.findByProduct(product);
        Assertions.assertThat(businessPlan).isEqualTo(businessPlans.get(0));

        List<BusinessPlan> businessPlans1 = businessPlanService.findByPart("superApp");
        Assertions.assertThat(businessPlan).isEqualTo(businessPlans1.get(0));
        Assertions.assertThat(businessPlans).isEqualTo(businessPlans1);

    }

//    @Commit
//    @Test
    void findByEmployeeTest() {
        Employee employee = new Employee();
            employee.setName("호영");
            employee.setDepartment("FC2-1");
            Employee employee1 = new Employee();
            employee1.setName("팀장");
            Employee employee2 = new Employee();
            employee2.setName("실장");
            employeeService.save(employee);
            employeeService.save(employee1);
            employeeService.save(employee2);

            //제품정보 등록
            Product product = new Product();
            product.setPart("superApp");
            productRepository.save(product);

            //사업계획 생성
        BusinessPlan businessPlan = new BusinessPlan();

        //결재정보 등록
        Approval approval = new Approval();
        approval.setApprovalType("합의");
        approval.setEmployee(employee1);
        approval.setBusinessPlan(businessPlan);
        approvalRepository.save(approval);

        Approval approval1 = new Approval();
        approval1.setApprovalType("결재");
        approval1.setEmployee(employee2);
        approval1.setBusinessPlan(businessPlan);
        approvalRepository.save(approval1);

            //사업계획 정보 등록
        businessPlan.setApprovalList(approvalRepository.findAll());
        businessPlan.setPlanYear(2022L);
        businessPlan.setStatus(SubmitStatus.UNCOMPLETED);
        businessPlan.setProduct(product);
        businessPlan.setPrice(10000L);
        businessPlan.setEmployee(employee);
        Long saveId = businessPlanService.save(businessPlan);

        List<BusinessPlan> businessPlans = businessPlanRepository.findByEmployee(employee);
        Assertions.assertThat(businessPlan).isEqualTo(businessPlans.get(0));

        List<BusinessPlan> businessPlans1 = businessPlanService.findByName("호영");
        Assertions.assertThat(businessPlan).isEqualTo(businessPlans1.get(0));
        Assertions.assertThat(businessPlans).isEqualTo(businessPlans1);

        List<BusinessPlan> businessPlans2 = businessPlanService.findByDepartment("FC2-1");
        Assertions.assertThat(businessPlan).isEqualTo(businessPlans2.get(0));
        Assertions.assertThat(businessPlans).isEqualTo(businessPlans2);

        }
}
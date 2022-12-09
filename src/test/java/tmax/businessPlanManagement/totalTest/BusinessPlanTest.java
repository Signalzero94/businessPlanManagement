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

import javax.persistence.EntityManager;
import java.sql.SQLOutput;
import java.util.ArrayList;
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

    EntityManager em;
    @Test
    void BusinessPlanServiceTest() {
        //인사정보 등록
        Employee employee = Employee.builder()
                .name("호영")
                .department("FC2-1")
                .build();
        Employee employee1 = Employee.builder()
                .name("팀장")
                .department("FC2-1")
                .build();

        employeeService.save(employee);
        employeeService.save(employee1);

        //제품정보 등록
        Product product = Product.builder()
                .part("superApp")
                .build();
        productRepository.save(product);

        //결재정보 등록
        Approval approval = Approval.builder()
                .approvalType("합의")
                .employee(employee1)
                .build();
        approvalRepository.save(approval);

        //사업계획 정보 등록
        BusinessPlan businessPlan = BusinessPlan.builder()
                .approvalList(approvalRepository.findAll())
                .planYear(2022L)
                .submitStatus(SubmitStatus.UNCOMPLETED)
                .product(product)
                .salesAmount(10000L)
                .employee(employee)
                .build();
        Long saveId = businessPlanService.save(businessPlan);

        Assertions.assertThat("호영").isEqualTo(businessPlanService.findById(saveId).getEmployee().getName());
    }

    @Test
    void findBySomethingTest() {
        Employee employee = Employee.builder()
                .name("호영")
                .department("FC2-1")
                .build();
        Employee employee1 = Employee.builder()
                .name("팀장")
                .department("FC2-1")
                .build();
        Employee employee2 = Employee.builder()
                .name("실장")
                .department("FC본부")
                .build();
        employeeService.save(employee);
        employeeService.save(employee1);
        employeeService.save(employee2);

        //제품정보 등록
        Product product = Product.builder()
                .part("superApp")
                .build();
        productRepository.save(product);

        //결재정보 등록
        Approval approval = Approval.builder()
                .approvalType("합의")
                .employee(employee1)
                .build();
        approvalRepository.save(approval);

        Approval approval1 = Approval.builder()
                .approvalType("결재")
                .employee(employee2)
                .build();
        approvalRepository.save(approval1);

        //사업계획 정보 등록
        BusinessPlan businessPlan = BusinessPlan.builder()
                .approvalList(approvalRepository.findAll())
                .planYear(2022L)
                .submitStatus(SubmitStatus.UNCOMPLETED)
                .product(product)
                .salesAmount(10000L)
                .employee(employee)
                .build();
        Long saveId = businessPlanService.save(businessPlan);

        approval.setBusinessPlan(businessPlan);
        approval1.setBusinessPlan(businessPlan);

        //사업년도로 찾기
        List<BusinessPlan> businessPlans = businessPlanService.findByPlanYear(2022l);
        System.out.println("businessPlans 수량 = " + businessPlans.size());
        Assertions.assertThat(businessPlan).isEqualTo(businessPlans.get(0));
    }

//    @Commit
//    @Test
    void findByProductPart() {
        Employee employee = Employee.builder()
                .name("호영")
                .department("FC2-1")
                .build();
        Employee employee1 = Employee.builder()
                .name("팀장")
                .department("FC2-1")
                .build();
        Employee employee2 = Employee.builder()
                .name("실장")
                .department("FC본부")
                .build();
        employeeService.save(employee);
        employeeService.save(employee1);
        employeeService.save(employee2);

        //제품정보 등록
        Product product = Product.builder()
                .part("superApp")
                .build();
        productRepository.save(product);

        //결재정보 등록
        Approval approval = Approval.builder()
                .approvalType("합의")
                .employee(employee1)
                .build();
        approvalRepository.save(approval);

        Approval approval1 = Approval.builder()
                .approvalType("결재")
                .employee(employee2)
                .build();
        approvalRepository.save(approval1);

        //사업계획 정보 등록
        BusinessPlan businessPlan = BusinessPlan.builder()
                .approvalList(approvalRepository.findAll())
                .planYear(2022L)
                .submitStatus(SubmitStatus.UNCOMPLETED)
                .product(product)
                .salesAmount(10000L)
                .employee(employee)
                .build();
        Long saveId = businessPlanService.save(businessPlan);

        approval.setBusinessPlan(businessPlan);
        approval1.setBusinessPlan(businessPlan);

        List<BusinessPlan> businessPlans = businessPlanRepository.findByProduct(product);
        Assertions.assertThat(businessPlan).isEqualTo(businessPlans.get(0));

        List<BusinessPlan> businessPlans1 = businessPlanService.findByPart("superApp");
        Assertions.assertThat(businessPlan).isEqualTo(businessPlans1.get(0));
        Assertions.assertThat(businessPlans).isEqualTo(businessPlans1);

    }

    @Commit
    @Test
    void findByEmployeeTest() {
        Employee employee = Employee.builder()
                .name("호영")
                .department("FC2-1")
                .build();
        Employee employee1 = Employee.builder()
                .name("팀장")
                .department("FC2-2")
                .build();
        Employee employee2 = Employee.builder()
                .name("실장")
                .department("FC본부")
                .build();

            employeeService.save(employee);
            employeeService.save(employee1);
            employeeService.save(employee2);

            //제품정보 등록
        Product product = Product.builder()
                .part("superApp")
                .build();
            productRepository.save(product);

        //결재정보 등록
        Approval approval = Approval.builder()
                .approvalType("합의")
                .employee(employee1)
                .build();
        approvalRepository.save(approval);

        Approval approval1 = Approval.builder()
                .approvalType("결재")
                .employee(employee2)
                .build();
        approvalRepository.save(approval1);

        //사업계획 정보 등록
        BusinessPlan businessPlan = BusinessPlan.builder()
                .approvalList(approvalRepository.findAll())
                .planYear(2022L)
                .submitStatus(SubmitStatus.UNCOMPLETED)
                .product(product)
                .salesAmount(10000L)
                .employee(employee)
                .build();
        Long saveId = businessPlanService.save(businessPlan);

        approval.setBusinessPlan(businessPlan);
        approval1.setBusinessPlan(businessPlan);

        List<BusinessPlan> businessPlans = businessPlanRepository.findByEmployee(employee);
        Assertions.assertThat(businessPlan).isEqualTo(businessPlans.get(0));

        List<BusinessPlan> businessPlans1 = businessPlanService.findByName("호영");
        Assertions.assertThat(businessPlan).isEqualTo(businessPlans1.get(0));
        Assertions.assertThat(businessPlans).isEqualTo(businessPlans1);

//        List<BusinessPlan> businessPlans2 = businessPlanService.findByDepartment("FC2-1");
//        Assertions.assertThat(businessPlan).isEqualTo(businessPlans2.get(0));
        }
    @Test
    @Commit
    void 양방향매핑확인() {
        Employee employee = Employee.builder()
                .name("호영")
                .department("FC2-1")
                .build();
        Employee employee1 = Employee.builder()
                .name("팀장")
                .department("FC2-1")
                .build();
        Employee employee2 = Employee.builder()
                .name("실장")
                .department("FC본부")
                .build();
        employeeService.save(employee);
        employeeService.save(employee1);
        employeeService.save(employee2);

        //제품정보 등록
        Product product = Product.builder()
                .part("superApp")
                .build();
        productRepository.save(product);

        //사업계획 정보 등록
        BusinessPlan businessPlan1 = BusinessPlan.builder()
                .planYear(2022L)
                .submitStatus(SubmitStatus.UNCOMPLETED)
                .product(product)
                .salesAmount(10000L)
                .employee(employee)
                .approvalList(new ArrayList<>())
                .build();
        businessPlanService.save(businessPlan1);

        BusinessPlan businessPlan2 = BusinessPlan.builder()
                .employee(employee1)
                .submitStatus(SubmitStatus.COMPLETED)
                .approvalList(new ArrayList<>())
                .salesAmount(20000L)
                .build();
        businessPlanService.save(businessPlan2);
        //결재정보 등록
        Approval approval1 = Approval.builder()
                .approvalType("합의")
                .employee(employee1)
                .businessPlan(businessPlan1)
                .build();
        approvalRepository.save(approval1);

        Approval approval2 = Approval.builder()
                .approvalType("결재")
                .employee(employee2)
                .businessPlan(businessPlan1)
                .build();
        approvalRepository.save(approval2);

        businessPlan1.saveApprovalsToPlan(approvalRepository.findByBusinessPlan(businessPlan1));

        System.out.println("---------------------------------------------");
        for (Approval a : businessPlan1.getApprovalList()) {
            System.out.println("사업계획 = " + a.getBusinessPlan());
            System.out.println("결재자명 = " + a.getEmployee().getName());
        }
        System.out.println("---------------------------------------------");
    }
    @Test
    @Commit
    void 부서로_사업계획찾기() {
        Employee employee = Employee.builder()
                .name("호영")
                .department("FC2-1")
                .build();
        Employee employee1 = Employee.builder()
                .name("팀장")
                .department("FC2-1")
                .build();
        Employee employee2 = Employee.builder()
                .name("실장")
                .department("FC본부")
                .build();
        Employee employee3 = Employee.builder()
                .name("팀원")
                .department("FC2-1")
                .build();

        employeeService.save(employee);
        employeeService.save(employee1);
        employeeService.save(employee2);
        employeeService.save(employee3);


        //제품정보 등록
        Product product = Product.builder()
                .part("superApp")
                .build();
        productRepository.save(product);

        //사업계획 정보 등록
        BusinessPlan businessPlan1 = BusinessPlan.builder()
                .planYear(2022L)
                .submitStatus(SubmitStatus.UNCOMPLETED)
                .product(product)
                .salesAmount(10000L)
                .employee(employee)
                .approvalList(new ArrayList<>())
                .build();
        businessPlanService.save(businessPlan1);

        BusinessPlan businessPlan2 = BusinessPlan.builder()
                .employee(employee1)
                .submitStatus(SubmitStatus.COMPLETED)
                .approvalList(new ArrayList<>())
                .salesAmount(20000L)
                .build();
        businessPlanService.save(businessPlan2);

        BusinessPlan businessPlan3 = BusinessPlan.builder()
                .employee(employee3)
                .submitStatus(SubmitStatus.UNCOMPLETED)
                .approvalList(new ArrayList<>())
                .salesAmount(25000L)
                .build();
        businessPlanService.save(businessPlan3);

        BusinessPlan businessPlan4 = BusinessPlan.builder()
                .employee(employee2)
                .approvalList(new ArrayList<>())
                .build();
        businessPlanService.save(businessPlan4);


        //결재정보 등록
        Approval approval1 = Approval.builder()
                .approvalType("합의")
                .employee(employee1)
                .businessPlan(businessPlan1)
                .build();
        approvalRepository.save(approval1);

        Approval approval2 = Approval.builder()
                .approvalType("결재")
                .employee(employee2)
                .businessPlan(businessPlan1)
                .build();
        approvalRepository.save(approval2);

        businessPlan1.saveApprovalsToPlan(approvalRepository.findByBusinessPlan(businessPlan1));

        List<BusinessPlan> businessPlans = businessPlanService.findByDepartment("FC2-1");

        for (BusinessPlan b : businessPlans) {
            System.out.println("FC2-1팀 사업계획 = " + b);
        }

        System.out.println("-----------------------------");
        for (BusinessPlan c : businessPlanService.findAll()) {
            System.out.println(c);
        }
    }
}
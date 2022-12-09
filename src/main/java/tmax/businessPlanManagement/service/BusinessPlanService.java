package tmax.businessPlanManagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tmax.businessPlanManagement.domain.Approval;
import tmax.businessPlanManagement.domain.BusinessPlan;
import tmax.businessPlanManagement.domain.Employee;
import tmax.businessPlanManagement.domain.Product;
import tmax.businessPlanManagement.repository.BusinessPlanRepository;
import tmax.businessPlanManagement.repository.EmployeeRepository;
import tmax.businessPlanManagement.repository.ProductRepository;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BusinessPlanService {

    private final BusinessPlanRepository businessPlanRepository;
    private final EmployeeRepository employeeRepository;
    private final ProductRepository productRepository;

    public Long save(BusinessPlan businessPlan) {
        businessPlanRepository.save(businessPlan);
        return businessPlan.getId();
    }

    public List<BusinessPlan> findAll() {
        return businessPlanRepository.findAll();
    }

    public BusinessPlan findById(Long id) {
        return businessPlanRepository.findById(id).get();
    }

    public List<BusinessPlan> findByPlanYear(Long planYear) {
        return businessPlanRepository.findByPlanYear(planYear);
    }

    public List<BusinessPlan> findByPart(String part) {
        return businessPlanRepository.findByProduct(productRepository.findByPart(part).get());
    }

    public List<BusinessPlan> findByName(String name) {
        return businessPlanRepository.findByEmployee(employeeRepository.findByName(name).get());
    }

    public List<BusinessPlan> findByDepartment(String department) {
        List<BusinessPlan> list = new ArrayList<>();
        for (Employee e : employeeRepository.findByDepartment(department)) {
            for (BusinessPlan b : businessPlanRepository.findByEmployee(e)) {
                list.add(b);
            }
        }
        return list;
    }
}
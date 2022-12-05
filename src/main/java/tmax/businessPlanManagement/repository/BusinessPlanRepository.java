package tmax.businessPlanManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tmax.businessPlanManagement.domain.BusinessPlan;
import tmax.businessPlanManagement.domain.Employee;
import tmax.businessPlanManagement.domain.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface BusinessPlanRepository extends JpaRepository<BusinessPlan, Long> {

    BusinessPlan save(BusinessPlan businessPlan);
    Optional<BusinessPlan> findById(Long id);
    List<BusinessPlan> findAll();   // 전체 조회
    List<BusinessPlan> findByPlanYear(Long planYear);   // 년도별 조회
    List<BusinessPlan> findByProduct(Product product);  // 상품별 조회
    List<BusinessPlan> findByEmployee(Employee employee); // 부서별 조회
}
//상품, 부서, 년도, 영업대표별 조회

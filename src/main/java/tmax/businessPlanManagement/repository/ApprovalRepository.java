package tmax.businessPlanManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tmax.businessPlanManagement.domain.Approval;
import tmax.businessPlanManagement.domain.BusinessPlan;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApprovalRepository extends JpaRepository<Approval, Long> {

    Approval save(Approval approval);
    List<Approval> findAll();
    Optional<Approval> findById(Long id);
    List<Approval> findByBusinessPlan(BusinessPlan businessPlan);

}

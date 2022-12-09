package tmax.businessPlanManagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tmax.businessPlanManagement.domain.Approval;
import tmax.businessPlanManagement.domain.BusinessPlan;
import tmax.businessPlanManagement.repository.ApprovalRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ApprovalService {

    private final ApprovalRepository approvalRepository;

    public void save(Approval approval) {
        approvalRepository.save(approval);
    }

    public List<Approval> findAll() {
        return approvalRepository.findAll();
    }

    public Approval findById(Long id) {
        return approvalRepository.findById(id).get();
    }

    public List<Approval> findByBusinessPlan(BusinessPlan businessPlan) {
        return approvalRepository.findByBusinessPlan(businessPlan);
    }
}

package tmax.businessPlanManagement.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Builder
@Table(name = "APPROVAL")
public class Approval {

    @Id
    @GeneratedValue
    @Column(name = "APPROVAL_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "BUSINESS_PLAN_ID")
    private BusinessPlan businessPlan;

    @Column(name = "APPROVAL_TYPE")
    private String approvalType;

    public Approval(Long id, Employee employee, BusinessPlan businessPlan, String approvalType) {
        this.id = id;
        this.employee = employee;
        this.businessPlan = businessPlan;
        this.approvalType = approvalType;
    }
    public void setBusinessPlan(BusinessPlan businessPlan) {
        this.businessPlan = businessPlan;
    }

}
package tmax.businessPlanManagement.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "approval")
public class Approval {

    @Id
    @GeneratedValue
    @Column(name = "approval_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "business_plan_id")
    private BusinessPlan businessPlan;

    private String approvalType;
}

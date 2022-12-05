package tmax.businessPlanManagement.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "business_plan")
public class BusinessPlan {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToMany(mappedBy = "businessPlan")
    private List<Approval> approvalList;

    private Long collectionPrice;
    private Long price;
    private Long planYear;

    @Enumerated(EnumType.STRING)
    private SubmitStatus status;
}

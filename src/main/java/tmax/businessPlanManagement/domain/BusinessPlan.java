package tmax.businessPlanManagement.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@Table(name = "BUSINESS_PLAN")
public class BusinessPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BusinessPlan_SEQ")
    @Column(name = "BUSINESS_PLAN_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;  //영업대표

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;    //제품

    @OneToMany(mappedBy = "businessPlan")
    private List<Approval> approvalList;    //결재목록

    @Column(name = "TARGET_AMOUNT_01")
    private Long targetAmount01;    //월별 목표금액
    @Column(name = "TARGET_AMOUNT_02")
    private Long targetAmount02;
    @Column(name = "TARGET_AMOUNT_03")
    private Long targetAmount03;
    @Column(name = "TARGET_AMOUNT_04")
    private Long targetAmount04;
    @Column(name = "TARGET_AMOUNT_05")
    private Long targetAmount05;
    @Column(name = "TARGET_AMOUNT_06")
    private Long targetAmount06;
    @Column(name = "TARGET_AMOUNT_07")
    private Long targetAmount07;
    @Column(name = "TARGET_AMOUNT_08")
    private Long targetAmount08;
    @Column(name = "TARGET_AMOUNT_09")
    private Long targetAmount09;
    @Column(name = "TARGET_AMOUNT_10")
    private Long targetAmount10;
    @Column(name = "TARGET_AMOUNT_11")
    private Long targetAmount11;
    @Column(name = "TARGET_AMOUNT_12")
    private Long targetAmount12;
    @Column(name = "TARGET_AMOUNT_YEAR")
    private Long targetAmountYear;  //해당년도 목표금액(월별 목표금액 합산)

    @Column(name = "ACHIEVE_AMOUNT_01")
    private Long achievementAmount01;   //월별 달성금액
    @Column(name = "ACHIEVE_AMOUNT_02")
    private Long achievementAmount02;
    @Column(name = "ACHIEVE_AMOUNT_03")
    private Long achievementAmount03;
    @Column(name = "ACHIEVE_AMOUNT_04")
    private Long achievementAmount04;
    @Column(name = "ACHIEVE_AMOUNT_05")
    private Long achievementAmount05;
    @Column(name = "ACHIEVE_AMOUNT_06")
    private Long achievementAmount06;
    @Column(name = "ACHIEVE_AMOUNT_07")
    private Long achievementAmount07;
    @Column(name = "ACHIEVE_AMOUNT_08")
    private Long achievementAmount08;
    @Column(name = "ACHIEVE_AMOUNT_09")
    private Long achievementAmount09;
    @Column(name = "ACHIEVE_AMOUNT_10")
    private Long achievementAmount10;
    @Column(name = "ACHIEVE_AMOUNT_11")
    private Long achievementAmount11;
    @Column(name = "ACHIEVE_AMOUNT_12")
    private Long achievementAmount12;

    @Column(name = "ORDER_AMOUNT")
    private Long orderAmount;   //수주액
    @Column(name = "SALES_AMOUNT")
    private Long salesAmount;   //매출액

    @Column(name = "PLAN_YEAR")
    private Long planYear;  //사업년도

    @Enumerated(EnumType.STRING)
    @Column(name = "SUBMIT_STATUS")
    private SubmitStatus submitStatus;    //상신여부

    public BusinessPlan(Long id, Employee employee, Product product, List<Approval> approvalList, Long targetAmount01,
                        Long targetAmount02, Long targetAmount03, Long targetAmount04, Long targetAmount05,
                        Long targetAmount06, Long targetAmount07, Long targetAmount08, Long targetAmount09,
                        Long targetAmount10, Long targetAmount11, Long targetAmount12, Long targetAmountYear,
                        Long achievementAmount01, Long achievementAmount02, Long achievementAmount03, Long achievementAmount04,
                        Long achievementAmount05, Long achievementAmount06, Long achievementAmount07, Long achievementAmount08,
                        Long achievementAmount09, Long achievementAmount10, Long achievementAmount11, Long achievementAmount12,
                        Long orderAmount, Long salesAmount, Long planYear, SubmitStatus submitStatus) {
        this.id = id;
        this.employee = employee;
        this.product = product;
        this.approvalList = approvalList;
        this.targetAmount01 = targetAmount01;
        this.targetAmount02 = targetAmount02;
        this.targetAmount03 = targetAmount03;
        this.targetAmount04 = targetAmount04;
        this.targetAmount05 = targetAmount05;
        this.targetAmount06 = targetAmount06;
        this.targetAmount07 = targetAmount07;
        this.targetAmount08 = targetAmount08;
        this.targetAmount09 = targetAmount09;
        this.targetAmount10 = targetAmount10;
        this.targetAmount11 = targetAmount11;
        this.targetAmount12 = targetAmount12;
        this.targetAmountYear = targetAmountYear;
        this.achievementAmount01 = achievementAmount01;
        this.achievementAmount02 = achievementAmount02;
        this.achievementAmount03 = achievementAmount03;
        this.achievementAmount04 = achievementAmount04;
        this.achievementAmount05 = achievementAmount05;
        this.achievementAmount06 = achievementAmount06;
        this.achievementAmount07 = achievementAmount07;
        this.achievementAmount08 = achievementAmount08;
        this.achievementAmount09 = achievementAmount09;
        this.achievementAmount10 = achievementAmount10;
        this.achievementAmount11 = achievementAmount11;
        this.achievementAmount12 = achievementAmount12;
        this.orderAmount = orderAmount;
        this.salesAmount = salesAmount;
        this.planYear = planYear;
        this.submitStatus = submitStatus;
    }
    public void saveApprovalsToPlan(@NotNull List<Approval> approvalList) {
        for (Approval a : approvalList) {
            this.getApprovalList().add(a);
        }
    }
}

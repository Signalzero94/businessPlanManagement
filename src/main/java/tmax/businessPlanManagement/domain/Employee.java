package tmax.businessPlanManagement.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Builder
@Table(name = "EMPLOYEE")
public class Employee {

    @Id
    @GeneratedValue
    @Column(name = "EMPLOYEE_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "COMPANY")
    private String company;

    @Column(name = "POSITION")
    private String position;

    @Column(name = "DEPARTMENT")
    private String department;

    public Employee(Long id, String name, String company, String position, String department) {
        this.id = id;
        this.name = name;
        this.company = company;
        this.position = position;
        this.department = department;
    }
}
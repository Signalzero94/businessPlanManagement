package tmax.businessPlanManagement.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue
    @Column(name = "employee_id")
    private Long id;

    private String name;
    private String company;
    private String position;
    private String department;

}

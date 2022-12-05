package tmax.businessPlanManagement.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue
    @Column(name = "product_id")
    private Long id;
    private String part; //제품명
}

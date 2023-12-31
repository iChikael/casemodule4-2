package com.cg.model;

import com.cg.model.dto.BillDetailDTO;
import com.cg.model.dto.BillViewDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "carts")
public class Cart extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // JPA: Java Persistent API -> c
    @Column(precision = 12, scale = 0, nullable = false)
    private BigDecimal totalAmount;

    @OneToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id", unique = true, nullable = false)
    private Customer customer;

    public Bill toBill() {
        return new Bill()
                .setId(id)
                .setCustomer(customer)
                .setTotalAmount(totalAmount)
                ;
    }


}

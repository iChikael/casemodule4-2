package com.cg.model;

import com.cg.model.dto.BillViewDTO;
import com.cg.model.dto.TransferViewDTO;
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
@Table(name = "bills")
public class Bill extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(precision = 12, scale = 0, nullable = false)
    private BigDecimal totalAmount;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    private Customer customer;


    public BillViewDTO toBillViewDTO(){
        return new BillViewDTO()
                .setId(id)
                .setTotalAmount(totalAmount)
                .setCustomer(customer.getFullName());
    }
}

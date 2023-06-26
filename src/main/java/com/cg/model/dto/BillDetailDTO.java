package com.cg.model.dto;

import com.cg.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class BillDetailDTO {

    public Long id;

    public String customer;
    public String titles;
    public Integer quantity;
    public BigDecimal price;
    public BigDecimal amount;

    public BillDetailDTO toBillDetailDTO() {
        BillDetailDTO billDetailDTO = new BillDetailDTO()
                .setId(id)
                .setTitles(titles)
                .setQuantity(quantity)
                .setPrice(price)
                .setAmount(amount)
                ;
        return billDetailDTO;
    }
}

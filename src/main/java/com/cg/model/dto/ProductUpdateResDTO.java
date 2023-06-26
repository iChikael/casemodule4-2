package com.cg.model.dto;

import com.cg.model.Product;
import com.cg.model.ProductAvatar;
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
public class ProductUpdateResDTO {

    private Long id;
    private String title;
    private BigDecimal price;
    private Long quantity;
    private String description;
    private String unit;
    private String categoryTitle;
    private ProductAvatarDTO avatar;



    public ProductUpdateResDTO(Product product, ProductAvatar productAvatar){
        this.id = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
        this.description = product.getDescription();
        this.unit = String.valueOf(product.getUnit());
        this.categoryTitle = String.valueOf(product.getCategory());
        this.avatar = productAvatar.toProductAvatarDTO();
    }

}

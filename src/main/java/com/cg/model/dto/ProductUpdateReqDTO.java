package com.cg.model.dto;

import com.cg.model.Category;
import com.cg.model.Product;
import com.cg.model.ProductAvatar;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class ProductUpdateReqDTO implements Validator {


    private String title;
    private String price;
    private Long quantity;
    private String description;
    private Long unitId;
    private Long categoryId;


    public ProductResDTO toProductDTO(Category category, ProductAvatar productAvatar){
        return new ProductResDTO()
                .setTitle(title)
                .setPrice(BigDecimal.valueOf(Long.parseLong(price)))
                .setDescription(description)
                .setCategory(String.valueOf(category))
                .setUnit(String.valueOf(unitId))
                .setAvatar(productAvatar.toProductAvatarDTO())
                ;
    }


    public Product toProduct(Category category, ProductAvatar productAvatar){
        return new Product()
                .setTitle(title)
                .setPrice(BigDecimal.valueOf(Long.parseLong(price)))
                .setDescription(description)
                .setCategory(category)
                .setAvatar(productAvatar)
                ;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return ProductUpdateReqDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProductUpdateReqDTO productUpdateReqDTO = (ProductUpdateReqDTO) target;

        String productName = productUpdateReqDTO.getTitle();
        String price = productUpdateReqDTO.getPrice();
        String quantity = String.valueOf(productUpdateReqDTO.getQuantity());
        String categoryId = String.valueOf(productUpdateReqDTO.getCategoryId());

        if (productName.length() == 0) {
            errors.rejectValue("productName", "productName.null", "Product name must not be null");
        }

        if (price != null && price.length() > 0) {
            if (!price.matches("(^$|[0-9]*$)")) {
                errors.rejectValue("price", "price.number", "Price must be a number");
//
            }
            if (price.length() > 8) {
                errors.rejectValue("price", "price.max", "The maximum product price is ten million VND.");
//
            }
            if (price.length() < 5) {
                errors.rejectValue("price", "price.min", "The minimum product price is ten thousand VND.");
//
            }
        } else {
            errors.rejectValue("price", "price.null", "Price must not be null");
        }

        if (quantity != null && quantity.length() > 0) {
            if (!quantity.matches("(^$|[0-9]*$)")) {
                errors.rejectValue("quantity", "quantity.number", "Quantity must be a number");
//
            }
            if (Long.parseLong(quantity) < 0) {
                errors.rejectValue("quantity", "quantity.min", "The minimum product quantity is 0");
//
            }
            if (quantity.length() > 4) {
                errors.rejectValue("quantity", "quantity.max", "The quantity of the product is not more than 1000.");
//
            }
        } else {
            errors.rejectValue("quantity", "price.null", "Quantity must not be null");
        }

        if (categoryId != null && categoryId.length() > 0) {
            if (!categoryId.matches("(^$|[0-9]*$)")) {
                errors.rejectValue("categoryId", "categoryId.number", "Category's id must be a number");
            }
        } else {
            errors.rejectValue("categoryId", "categoryId.null", "Category's id must not be null");
        }

    }
}

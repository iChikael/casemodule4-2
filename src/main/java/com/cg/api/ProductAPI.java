package com.cg.api;

import com.cg.exception.DataInputException;
import com.cg.exception.ResourceNotFoundException;
import com.cg.model.Category;
import com.cg.model.Customer;
import com.cg.model.Product;
import com.cg.model.ProductAvatar;
import com.cg.model.dto.*;
import com.cg.service.category.ICategoryService;
import com.cg.service.product.IProductService;
import com.cg.service.product.ProductServiceImpl;
import com.cg.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductAPI {

    @Autowired
    private IProductService productService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private AppUtils appUtils;

    @GetMapping
    public ResponseEntity<?> getAll() {

        List<ProductResDTO> productResDTOS = productService.getAllProductResDTO();

        return new ResponseEntity<>(productResDTOS, HttpStatus.OK);

    }

    @PostMapping("/add")
//    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> create(ProductCreateReqDTO productCreateReqDTO) {

        ProductCreateResDTO productCreateResDTO = productService.create(productCreateReqDTO);

        return new ResponseEntity<>(productCreateResDTO, HttpStatus.OK);
    }


//        @PatchMapping("/{id}")
//    public ResponseEntity<?> update(@PathVariable Long id,
//                                    MultipartFile file,
//                                    @Validated ProductUpdateReqDTO productUpdateReqDTO,
//                                    BindingResult bindingResult
//    ) throws IOException {
//        new ProductUpdateReqDTO().validate(productUpdateReqDTO, bindingResult);
//
//        Product productOptional = productService
//                .findById(id)
//                .orElseThrow(
//                        () -> new ResourceNotFoundException("Not found this product to update")
//                );
//
//        Category categoryOptional = categoryService.findById(Long.parseLong(String.valueOf(productUpdateReqDTO.getCategoryId())))
//                .orElseThrow(
//                        () -> new DataInputException("Not found this category")
//                );
//
//        if (bindingResult.hasFieldErrors()) {
//            return appUtils.mapErrorToResponse(bindingResult);
//        }
//
//        ProductUpdateResDTO productUpdateResDTO;
//
//        if (file == null) {
//            ProductAvatar productAvatar = productOptional.getAvatar();
//            ProductResDTO productDTO = productUpdateReqDTO.toProductDTO(categoryOptional, productAvatar);
//            Product product = productDTO
//                    .toProduct()
//                    .setId(id);
//            productUpdateResDTO = productService.update(product);
//        } else {
//            productUpdateResDTO = productService.updateWithMedia(file, id, productUpdateReqDTO);
//        }
//        return new ResponseEntity<>(productUpdateResDTO, HttpStatus.OK);
//    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        Product product = productService
                .findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Not found this product to delete")
                );

        productService.delete(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        ProductResDTO productResDTO = productService
                .getProductResDTOByIdDeletedIsFalse(id)
                .orElseThrow(
                        ()-> new ResourceNotFoundException("Not found this product"));

        return new ResponseEntity<>(productResDTO, HttpStatus.OK);
    }


}

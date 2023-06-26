package com.cg.service.product;

import com.cg.model.Product;
import com.cg.model.dto.*;
import com.cg.service.IGeneralService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IProductService extends IGeneralService<Product, Long> {

    ProductCreateResDTO create(ProductCreateReqDTO productCreateReqDTO);

    ProductUpdateResDTO update(Product product);

    ProductUpdateResDTO updateWithMedia(MultipartFile MediaFile, Long id, ProductUpdateReqDTO productUpdateReqDTO) throws IOException;

    List<ProductResDTO> getAllProductResDTO();

    Optional<ProductResDTO> getProductResDTOByIdDeletedIsFalse(Long id);


}

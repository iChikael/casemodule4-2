package com.cg.service.product;

import com.cg.exception.DataInputException;
import com.cg.model.Category;
import com.cg.model.Product;
import com.cg.model.ProductAvatar;
import com.cg.model.Unit;
import com.cg.model.dto.*;
import com.cg.repository.CategoryRepository;
import com.cg.repository.ProductAvatarRepository;
import com.cg.repository.ProductRepository;
import com.cg.repository.UnitRepository;
import com.cg.service.uploadMedia.IUploadMediaService;
import com.cg.utils.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
@Scope("prototype")
@Transactional
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UnitRepository unitRepository;

    @Autowired
    private ProductAvatarRepository productAvatarRepository;

    @Autowired
    private IUploadMediaService uploadMediaService;

    @Autowired
    private UploadUtils uploadUtils;

    @Override
    public List<Product> findAll() {
        return null;
    }

    @Override
    public List<ProductResDTO> getAllProductResDTO() {
        return productRepository.getAllProductResDTO();
    }

    @Override
    public Optional<ProductResDTO> getProductResDTOByIdDeletedIsFalse(Long id) {
        return Optional.empty();
    }

//    @Override
//    public Optional<ProductResDTO> getProductResDTOByIdDeletedIsFalse(Long id) {
//        return productRepository.getProductResDTOByIdDeletedIsFalse(id);
//    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public ProductCreateResDTO create(ProductCreateReqDTO productCreateReqDTO) {

        Category category = categoryRepository.findById(productCreateReqDTO.getCategoryId()).orElseThrow(() -> {
            throw new DataInputException("Danh mục không hợp lệ");
        });

        Unit unit = unitRepository.findById(productCreateReqDTO.getUnitId()).orElseThrow(() -> {
           throw new DataInputException("Đơn vị không hợp lệ");
        });

        ProductAvatar productAvatar = new ProductAvatar();
        productAvatarRepository.save(productAvatar);

        uploadAndSaveProductImage(productCreateReqDTO.getAvatar(), productAvatar);

        BigDecimal price = BigDecimal.valueOf(Long.parseLong(productCreateReqDTO.getPrice()));

        Product product = new Product();
        product.setTitle(productCreateReqDTO.getTitle());
        product.setPrice(price);
        product.setQuantity(productCreateReqDTO.getQuantity());
        product.setDescription(productCreateReqDTO.getDescription());
        product.setUnit(unit);
        product.setCategory(category);
        product.setAvatar(productAvatar);
        productRepository.save(product);


        return product.toProductCreateResDTO();
    }



    @Override
    public ProductUpdateResDTO update(Product product) {
        productRepository.save(product);
        return new ProductUpdateResDTO(product, product.getAvatar());
    }


    @Override
    public ProductUpdateResDTO updateWithMedia(MultipartFile MediaFile, Long id, ProductUpdateReqDTO productUpdateReqDTO) throws IOException {
        //Tổng hợp lại kiến thức

        //3 dòng đầu này để xử lý Media
        ProductAvatar productAvatar = productRepository.findById(id).get().getAvatar();
        uploadMediaService.destroyImage(productAvatar.getCloudId(), uploadUtils.buildImageUploadParams(productAvatar));
        uploadAndSaveProductImage(MediaFile, productAvatar);

        //2 dòng này xử lý category
        Category category = new Category();
        category.setId(Long.parseLong(String.valueOf(productUpdateReqDTO.getCategoryId())));
        category.setTitle(productUpdateReqDTO.getTitle());


        //dòng còn lại xử lý các trường còn lại trong product
        Product product = productUpdateReqDTO.toProduct(category, productAvatar);
        product.setId(id);
        product = productRepository.save(product);

        //chuyển sang res để render
        return new ProductUpdateResDTO(product, productAvatar);
    }



    private void uploadAndSaveProductImage(MultipartFile file, ProductAvatar productAvatar) {
        try {
            Map uploadResult = uploadMediaService.uploadImage(file, uploadUtils.buildImageUploadParams(productAvatar));
            String fileUrl = (String) uploadResult.get("secure_url");
            String fileFormat = (String) uploadResult.get("format");
            Integer width = (Integer) uploadResult.get("width");
            Integer height = (Integer) uploadResult.get("height");

            productAvatar.setFileName(productAvatar.getId() + "." + fileFormat);
            productAvatar.setFileUrl(fileUrl);
            productAvatar.setFileFolder(UploadUtils.IMAGE_UPLOAD_FOLDER);
            productAvatar.setCloudId(productAvatar.getFileFolder() + "/" + productAvatar.getId());
            productAvatar.setWidth(width);
            productAvatar.setHeight(height);
            productAvatarRepository.save(productAvatar);

        } catch (IOException e) {
            e.printStackTrace();
            throw new DataInputException("Upload hình ảnh thất bại");
        }
    }

//    private void uploadAndSaveProductImage(ProductUpdateReqDTO productUpdateReqDTO, ProductAvatar productAvatar) {
//        try {
//            Map uploadResult = uploadMediaService.uploadImage(productUpdateReqDTO.getAvatar(), uploadUtils.buildImageUploadParams(productAvatar));
//            String fileUrl = (String) uploadResult.get("secure_url");
//            String fileFormat = (String) uploadResult.get("format");
//            Integer width = (Integer) uploadResult.get("width");
//            Integer height = (Integer) uploadResult.get("height");
//
//            productAvatar.setFileName(productAvatar.getId() + "." + fileFormat);
//            productAvatar.setFileUrl(fileUrl);
//            productAvatar.setFileFolder(UploadUtils.IMAGE_UPLOAD_FOLDER);
//            productAvatar.setCloudId(productAvatar.getFileFolder() + "/" + productAvatar.getId());
//            productAvatar.setWidth(width);
//            productAvatar.setHeight(height);
//            productAvatarRepository.save(productAvatar);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new DataInputException("Upload hình ảnh thất bại");
//        }
//    }

    @Override
    public Product save(Product product) {
        return null;
    }

    @Override
    public void delete(Product product) {

    }

    @Override
    public void deleteById(Long id) {

    }
}

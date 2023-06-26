package com.cg.repository;

import com.cg.model.Product;
import com.cg.model.dto.ProductResDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    @Query("SELECT NEW com.cg.model.dto.ProductResDTO (" +
                "pr.id," +
                "pr.title," +
                "pr.price," +
                "pr.quantity,"+
                "pr.description," +
                "pr.unit.title," +
                "pr.category.title," +
                "pr.avatar " +
            ")" +
            "FROM Product AS pr "
    )
    List<ProductResDTO> getAllProductResDTO();



//    @Query(value = "SELECT NEW com.cg.model.dto.ProductResDTO (" +
//            "p.id, " +
//            "p.title, " +
//            "p.price, " +
//            "p.quantity, " +
//            "p.description, " +
//            "pa.id," +
//            "pa.fileFolder, " +
//            "pa.fileName, " +
//            "pa.fileUrl, " +
//            "p.category" +
//            ") " +
//            "FROM Product AS p " +
//            "LEFT JOIN ProductAvatar AS pa " +
//            "ON p.productAvatar = pa " +
//            "WHERE p.deleted = false " +
//            "AND p.id = :id"
//    )
//    Optional<ProductResDTO> getProductResDTOByIdDeletedIsFalse(@Param("id") Long id);
}

package com.cg.repository;

import com.cg.model.BillDetail;
import com.cg.model.dto.BillDetailDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillDetailRepository extends JpaRepository<BillDetail, Long> {

    List<BillDetailDTO> findAllByBillId(Long billId);
}

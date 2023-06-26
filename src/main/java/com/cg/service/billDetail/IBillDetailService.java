package com.cg.service.billDetail;

import com.cg.model.BillDetail;
import com.cg.model.dto.BillDetailDTO;
import com.cg.service.IGeneralService;

import java.util.List;

public interface IBillDetailService extends IGeneralService<BillDetail, Long> {
    List<BillDetailDTO> findBillDetailDTOByBillId(Long billId);
}

package com.cg.service.bill;

import com.cg.model.Bill;
import com.cg.model.Customer;
import com.cg.model.dto.BillDetailDTO;
import com.cg.model.dto.BillViewDTO;
import com.cg.model.dto.TransferViewDTO;
import com.cg.service.IGeneralService;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.List;

public interface IBillService extends IGeneralService<Bill, Long> {

    void pay(Customer customer);

    List<BillViewDTO> findAllBillViewDTO();

    BigDecimal getAllFeesAmount();

}

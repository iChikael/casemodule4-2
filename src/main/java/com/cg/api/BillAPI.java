package com.cg.api;


import com.cg.exception.UnauthorizedException;
import com.cg.model.BillDetail;
import com.cg.model.Customer;
import com.cg.model.User;
import com.cg.model.dto.BillDetailDTO;
import com.cg.model.dto.BillHistoryResponse;
import com.cg.model.dto.BillViewDTO;
import com.cg.model.dto.TransferViewDTO;
import com.cg.service.bill.IBillService;
import com.cg.service.billDetail.IBillDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/bills")
public class BillAPI {

    @Autowired
    private IBillService billService;

    @Autowired
    private IBillDetailService billDetailService;

    @PostMapping("/pay")
    public ResponseEntity<?> pay() {


        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/revenue")
    public ResponseEntity<BillHistoryResponse> showBillHistory() {
        List<BillViewDTO> billViewDTOList = billService.findAllBillViewDTO();
        BigDecimal totalFeesAmount = billService.getAllFeesAmount();

        BillHistoryResponse response = new BillHistoryResponse();
        response.setBillViewDTOList(billViewDTOList);
        response.setTotalFeesAmount(totalFeesAmount);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/detail/{billId}")
    public ResponseEntity<?> showBillDetail(@PathVariable Long billId) {
        List<BillDetailDTO> billDetailDTOList = billDetailService.findBillDetailDTOByBillId(billId);

        return new ResponseEntity<>(billDetailDTOList, HttpStatus.OK);
    }

}

package com.cg.service.billDetail;

import com.cg.model.BillDetail;
import com.cg.model.dto.BillDetailDTO;
import com.cg.repository.BillDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class BillDetailServiceImpl implements IBillDetailService {

    @Autowired
    private BillDetailRepository billDetailRepository;

    @Override
    public List<BillDetail> findAll() {
        return null;
    }

    @Override
    public Optional<BillDetail> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public BillDetail save(BillDetail billDetail) {
        return null;
    }

    @Override
    public void delete(BillDetail billDetail) {

    }

    @Override
    public void deleteById(Long id) {

    }

//    @Override
//    public List<BillDetailDTO> findAllBillDetailDTO() {
//
//    }

//    @Override
//    public List<BillDetailDTO> findBillDetailDTOByBillId() {
//        List<BillDetailDTO> billDetailDTOList = billDetailRepository
//                .findAll()
//                .stream()
//                .map(bill -> {
//                    return bill.toBillDetailDTO();
//                }).collect(Collectors.toList());
//        return billDetailDTOList;
//    }


    @Override
    public List<BillDetailDTO> findBillDetailDTOByBillId(Long billId) {
        List<BillDetailDTO> billDetailDTOList = billDetailRepository
                .findAllByBillId(billId)
                .stream()
                .map(BillDetailDTO ::toBillDetailDTO)
                .collect(Collectors.toList());
        return billDetailDTOList;
    }
}

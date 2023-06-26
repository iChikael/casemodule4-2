package com.cg.service.bill;

import com.cg.model.Bill;
import com.cg.model.Customer;
import com.cg.model.dto.BillDetailDTO;
import com.cg.model.dto.BillViewDTO;
import com.cg.repository.BillDetailRepository;
import com.cg.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class BillServiceImpl implements IBillService{

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private BillDetailRepository billDetailRepository;

    @Override
    public List<Bill> findAll() {
        return null;
    }

    @Override
    public Optional<Bill> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void pay(Customer customer) {

    }

    @Override
    public List<BillViewDTO> findAllBillViewDTO() {
        List<BillViewDTO> billViewDTOList = billRepository
                .findAll()
                .stream()
                .map(bill -> {
                    return bill.toBillViewDTO();
                }).collect(Collectors.toList());
        return billViewDTOList;
    }

    @Override
    public BigDecimal getAllFeesAmount() {
        return billRepository.getAllFeesAmount();
    }



    @Override
    public Bill save(Bill bill) {
        return null;
    }

    @Override
    public void delete(Bill bill) {

    }

    @Override
    public void deleteById(Long id) {

    }
}

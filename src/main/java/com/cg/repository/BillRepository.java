package com.cg.repository;

import com.cg.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

    @Query("SELECT SUM(tr.totalAmount) FROM Bill AS tr")
    BigDecimal getAllFeesAmount();
}



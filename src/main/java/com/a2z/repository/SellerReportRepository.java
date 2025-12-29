package com.a2z.repository;

import com.a2z.model.SellerReport;
import com.a2z.service.SellerReportService;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Chetanand Meher
 */
public interface SellerReportRepository extends JpaRepository<SellerReport, Long> {

    SellerReport findBySellerId(long sellerId);
}

package com.a2z.service.impl;

import com.a2z.exception.SellerException;
import com.a2z.model.SellerReport;
import com.a2z.repository.SellerReportRepository;
import com.a2z.service.SellerReportService;
import com.a2z.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Chetanand Meher
 */
@Service
@RequiredArgsConstructor
public class SellerReportServiceImpl implements SellerReportService {

    private final SellerReportRepository sellerReportRepository;
    private final SellerService sellerService;

    @Override
    public SellerReport getSellerReportBySellerId(Long sellerId) throws SellerException {

        SellerReport sellerReport = sellerReportRepository.findBySellerId(sellerId);
        if(sellerReport==null){
            SellerReport newSellerReport = SellerReport.builder()
                    .seller(sellerService.getSellerById(sellerId))
                    .build();
            return sellerReportRepository.save(newSellerReport);
        }
        return sellerReport;
    }

    @Override
    public SellerReport updateSellerReport(long id, SellerReport sellerReport) {
        return sellerReportRepository.save(sellerReport);
    }
}

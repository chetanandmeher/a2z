package com.a2z.service;

import com.a2z.exception.SellerException;
import com.a2z.model.SellerReport;

public interface SellerReportService {
    SellerReport getSellerReportBySellerId(Long sellerId) throws SellerException;
    SellerReport updateSellerReport(long sellerId, SellerReport sellerReport);

}

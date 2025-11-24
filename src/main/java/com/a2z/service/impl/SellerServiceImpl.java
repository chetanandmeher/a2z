package com.a2z.service.impl;

import com.a2z.config.JwtProvider;
import com.a2z.enums.AccountStatus;
import com.a2z.enums.USER_ROLE;
import com.a2z.model.Address;
import com.a2z.model.Seller;
import com.a2z.repository.AddressRepository;
import com.a2z.repository.SellerRepository;
import com.a2z.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Chetanand Meher
 */
@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;
    private final JwtProvider jwtProvider;
    private final AddressRepository addressRepository;


    @Override
    public Seller getSellerProfileByJwt(String jwt) {
        String email = jwtProvider.getEmailFromJwt(jwt);
        return sellerRepository.findByEmail(email);
    }


    @Override
    public Seller createSeller(Seller seller) throws Exception {
        Seller sellerExist = sellerRepository.findByEmail(seller.getEmail());
        if (sellerExist != null) {
            throw new Exception("Seller with email " + seller.getEmail() + " already exists.");
        }
        Address newAddress = addressRepository.save(seller.getPickupAddress());

        Seller newSeller = Seller.builder()
                .name(seller.getName())
                .email(seller.getEmail())
                .password(seller.getPassword())
                .pickupAddress(newAddress)
                .accountStatus(AccountStatus.PENDING)
                .GSTIN(seller.getGSTIN())
                .mobile(seller.getMobile())
                .bankDetails(seller.getBankDetails())
                .businessDetails(seller.getBusinessDetails())
                .role(USER_ROLE.SELLER)
                .build();

        return sellerRepository.save(newSeller);
    }


    @Override
    public Seller getSellerById(Long id) {
        return sellerRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Seller not found with id: " + id));
    }


    @Override
    public Seller getSellerByEmail(String email) {
        Seller seller = sellerRepository.findByEmail(email);
        if (seller == null) {
            throw new RuntimeException("Seller not found with email: " + email);
        }
        return seller;
    }

    /**
     * @param status
     * @return
     */
    @Override
    public List<Seller> getAllSellerByAccountStatus(AccountStatus status) {
        return sellerRepository.findAllByAccountStatus(status);
    }

    /**
     * @param id
     * @param seller
     * @return
     */
    @Override
    public Seller updateSeller(Long id, Seller seller) {
        Seller existingSeller = sellerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seller not found with id: " + id));

        // update the fields
        if (seller.getName() != null) {
            existingSeller.setName(seller.getName());
        }
        if (seller.getMobile() != null) {
            existingSeller.setMobile(seller.getMobile());
        }
        if (seller.getGSTIN() != null) {
            existingSeller.setGSTIN(seller.getGSTIN());
        }
        if (seller.getBankDetails() != null) {
            existingSeller.setBankDetails(seller.getBankDetails());
        }
        if (seller.getBusinessDetails() != null &&
                seller.getBusinessDetails().getBusinessName() != null) {
            existingSeller.getBusinessDetails().setBusinessName(
                    seller.getBusinessDetails().getBusinessName());
        }
        if (seller.getBankDetails() != null
                && seller.getBankDetails().getAccountNumber() != null
                && seller.getBankDetails().getIfscCode() != null
                && seller.getBankDetails().getAccountHolderName() != null
        ) {
            existingSeller.getBankDetails().setAccountNumber(
                    seller.getBankDetails().getAccountNumber());
            existingSeller.getBankDetails().setIfscCode(
                    seller.getBankDetails().getIfscCode());
            existingSeller.getBankDetails().setAccountHolderName(
                    seller.getBankDetails().getAccountHolderName());
        }
        if (seller.getEmail() != null) {
            existingSeller.setEmail(seller.getEmail());
        }
        if (seller.getPickupAddress() != null
                && seller.getPickupAddress().getAddress() != null
                && seller.getPickupAddress().getCity() != null
                && seller.getPickupAddress().getState() != null
                && seller.getPickupAddress().getMobile() != null
        ) {
            existingSeller.getPickupAddress().setAddress(seller.getPickupAddress().getAddress());
            existingSeller.getPickupAddress().setCity(seller.getPickupAddress().getCity());
            existingSeller.getPickupAddress().setState(seller.getPickupAddress().getState());
            existingSeller.getPickupAddress().setPinCode(seller.getPickupAddress().getPinCode());
            existingSeller.getPickupAddress().setMobile(seller.getPickupAddress().getMobile());
        }
        return sellerRepository.save(existingSeller);
    }

    /**
     * @param id
     */
    @Override
    public void deleteSeller(Long id) {
        Seller existingSeller = this.getSellerById(id);
        sellerRepository.delete(existingSeller);
    }

    /**
     * @param email
     * @param otp
     * @return
     */
    @Override
    public Seller verifyEmail(String email, String otp) {
        Seller seller = sellerRepository.findByEmail(email);
        seller.setEmailVerified(true);

        return sellerRepository.save(seller);
    }

    /**
     * @param sellerId
     * @param status
     * @return
     */
    @Override
    public Seller updateSellerAccountStatus(Long sellerId, AccountStatus status) {
         Seller seller = getSellerById(sellerId);
         seller.setAccountStatus(status);
        return sellerRepository.save(seller);
    }
}

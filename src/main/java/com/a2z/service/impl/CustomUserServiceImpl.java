package com.a2z.service.impl;

import com.a2z.enums.USER_ROLE;
import com.a2z.model.Seller;
import com.a2z.model.User;
import com.a2z.repository.SellerRepository;
import com.a2z.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chetanand Meher
 */
@Service
@RequiredArgsConstructor
public class CustomUserServiceImpl  implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(CustomUserServiceImpl.class);
    private final UserRepository userRepository;
    private static final String SELLER_PREFIX = "seller_";
    private final SellerRepository sellerRepository;

    /**
     * @param username the username identifying the user whose data is required.
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Loading user by username: " + username);
        if (username.startsWith(SELLER_PREFIX)) {
            String actualUsername = username.substring(SELLER_PREFIX.length());
            Seller seller = sellerRepository.findByEmail(actualUsername);
            if (seller != null) {
                return buildUserDetails(seller.getEmail(), seller.getPassword(), seller.getRole());
            }

        }else{
            User user = userRepository.findByEmail(username);
            if (user != null) {
                return buildUserDetails(user.getEmail(), user.getPassword(), user.getRole());
            }
        }
        throw new UsernameNotFoundException("User not found with username: " + username);
    }
    private UserDetails buildUserDetails(String email, String password, USER_ROLE role) {
        if (role==null){
            role = USER_ROLE.CUSTOMER;
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.name()));
        return new org.springframework.security.core.userdetails.User(email, password, authorities);
    }
}

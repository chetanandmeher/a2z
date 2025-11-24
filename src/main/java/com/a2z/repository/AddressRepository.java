package com.a2z.repository;

import com.a2z.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Chetanand Meher
 */
public interface AddressRepository extends JpaRepository<Address, Long> {

}

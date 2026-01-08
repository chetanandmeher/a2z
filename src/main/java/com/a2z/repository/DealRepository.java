package com.a2z.repository;

import com.a2z.model.Deal;
import org.hibernate.query.criteria.JpaDerivedRoot;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Chetanand Meher
 */
public interface DealRepository extends JpaRepository<Deal, Long> {
}

package com.exoticlanka.backend.repo;

import com.exoticlanka.backend.entity.Customer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface CustomerRepo extends JpaRepository<Customer,Long> {

    public List<Customer> findAllByName(String name);
    
    @Query(value = "SELECT * FROM customer WHERE name LIKE ?1 OR address LIKE ?1",nativeQuery = true)
    public List<Customer> searchCustomers(String searchText, Pageable pageable);

    @Query(value = "SELECT COUNT(*) FROM customer WHERE name LIKE ?1 OR address LIKE ?1",nativeQuery = true)
    public Long countCustomers(String searchText);
}

package com.crm.demo.repository;

import com.crm.demo.entity.Customer;
import com.crm.demo.entity.CustomerStatus;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByStatus(CustomerStatus status);

    @Query("SELECT c FROM Customer c WHERE c.name LIKE CONCAT('%', :name, '%')")
    List<Customer> searchByName(String name);

    default List<Customer> findActiveCustomers() {
        return findByStatus(CustomerStatus.ACTIVE);
    }

    long countByStatus(CustomerStatus status);
}

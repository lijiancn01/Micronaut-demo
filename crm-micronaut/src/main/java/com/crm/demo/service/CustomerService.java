package com.crm.demo.service;

import com.crm.demo.entity.Customer;
import com.crm.demo.entity.CustomerStatus;
import com.crm.demo.repository.CustomerRepository;
import io.micronaut.transaction.annotation.Transactional;
import jakarta.inject.Singleton;

import java.util.List;

@Singleton
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    public List<Customer> getActiveCustomers() {
        return customerRepository.findActiveCustomers();
    }

    public List<Customer> searchCustomers(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return getAllCustomers();
        }
        return customerRepository.searchByName(keyword);
    }

    @Transactional
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Transactional
    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer == null) {
            return null;
        }
        customer.setName(updatedCustomer.getName());
        customer.setEmail(updatedCustomer.getEmail());
        customer.setPhone(updatedCustomer.getPhone());
        customer.setCompany(updatedCustomer.getCompany());
        customer.setStatus(updatedCustomer.getStatus());
        return customerRepository.update(customer);
    }

    @Transactional
    public boolean deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer == null) {
            return false;
        }
        customerRepository.delete(customer);
        return true;
    }

    @Transactional
    public Customer toggleCustomerStatus(Long id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer == null) {
            return null;
        }
        customer.setStatus((customer.getStatus() == CustomerStatus.ACTIVE)
                ? CustomerStatus.INACTIVE
                : CustomerStatus.ACTIVE);
        return customerRepository.update(customer);
    }

    public long getTotalCount() {
        return customerRepository.count();
    }

    public long getActiveCount() {
        return customerRepository.countByStatus(CustomerStatus.ACTIVE);
    }

    public long getInactiveCount() {
        return customerRepository.countByStatus(CustomerStatus.INACTIVE);
    }
}

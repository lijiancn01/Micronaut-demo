package com.crm.demo.config;

import com.crm.demo.entity.Customer;
import com.crm.demo.entity.CustomerStatus;
import com.crm.demo.repository.CustomerRepository;
import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.event.annotation.EventListener;
import io.micronaut.transaction.annotation.Transactional;
import jakarta.inject.Singleton;

@Singleton
public class DataInitializer {

    private final CustomerRepository customerRepository;

    public DataInitializer(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @EventListener
    @Transactional
    public void onStartup(StartupEvent event) {
        if (customerRepository.count() == 0) {
            addCustomer("张三", "zhangsan@example.com", "13800138000", "北京科技有限公司", CustomerStatus.ACTIVE);
            addCustomer("李四", "lisi@example.com", "13800138001", "上海贸易集团", CustomerStatus.ACTIVE);
            addCustomer("王五", "wangwu@example.com", "13800138002", "广州制造企业", CustomerStatus.INACTIVE);
            addCustomer("赵六", "zhaoliu@example.com", "13800138003", "深圳互联网公司", CustomerStatus.ACTIVE);
            addCustomer("钱七", "qianqi@example.com", "13800138004", "成都软件园", CustomerStatus.ACTIVE);
        }
    }

    private void addCustomer(String name, String email, String phone, String company, CustomerStatus status) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        customer.setPhone(phone);
        customer.setCompany(company);
        customer.setStatus(status);
        customerRepository.save(customer);
    }
}

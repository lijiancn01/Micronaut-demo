package com.crm.demo.controller;

import com.crm.demo.entity.Customer;
import com.crm.demo.service.CustomerService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.validation.Validated;
import jakarta.validation.Valid;

import java.util.List;

@Controller("/api/customers")
@Validated
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Get
    public HttpResponse<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return HttpResponse.ok(customers);
    }

    @Get("/{id}")
    public HttpResponse<Customer> getCustomerById(Long id) {
        Customer customer = customerService.getCustomerById(id);
        if (customer == null) {
            return HttpResponse.notFound();
        }
        return HttpResponse.ok(customer);
    }

    @Get("/search")
    public HttpResponse<List<Customer>> searchCustomers(@QueryValue String keyword) {
        List<Customer> customers = customerService.searchCustomers(keyword);
        return HttpResponse.ok(customers);
    }

    @Get("/active")
    public HttpResponse<List<Customer>> getActiveCustomers() {
        List<Customer> customers = customerService.getActiveCustomers();
        return HttpResponse.ok(customers);
    }

    @Post
    public HttpResponse<Customer> createCustomer(@Body @Valid Customer customer) {
        Customer created = customerService.createCustomer(customer);
        return HttpResponse.created(created);
    }

    @Put("/{id}")
    public HttpResponse<Customer> updateCustomer(Long id, @Body @Valid Customer customer) {
        Customer updated = customerService.updateCustomer(id, customer);
        if (updated == null) {
            return HttpResponse.notFound();
        }
        return HttpResponse.ok(updated);
    }

    @Delete("/{id}")
    public HttpResponse<Void> deleteCustomer(Long id) {
        boolean deleted = customerService.deleteCustomer(id);
        if (!deleted) {
            return HttpResponse.notFound();
        }
        return HttpResponse.noContent();
    }

    @Patch("/{id}/toggle-status")
    public HttpResponse<Customer> toggleCustomerStatus(Long id) {
        Customer customer = customerService.toggleCustomerStatus(id);
        if (customer == null) {
            return HttpResponse.notFound();
        }
        return HttpResponse.ok(customer);
    }

    @Get("/stats")
    public HttpResponse<Stats> getStats() {
        return HttpResponse.ok(new Stats(
                customerService.getTotalCount(),
                customerService.getActiveCount(),
                customerService.getInactiveCount()
        ));
    }

    public static class Stats {
        public final long total;
        public final long active;
        public final long inactive;

        public Stats(long total, long active, long inactive) {
            this.total = total;
            this.active = active;
            this.inactive = inactive;
        }
    }
}

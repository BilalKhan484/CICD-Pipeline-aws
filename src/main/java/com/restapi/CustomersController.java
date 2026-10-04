package com.restapi;

import com.restapi.model.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomersController {

    // GET /customers -> Returns a JSON array of customers
    @GetMapping
    public List<Customer> getCustomers() {
        return Arrays.asList(
            new Customer("1", "John Doe", "john.doe@example.com"),
            new Customer("2", "Jane Smith", "jane.smith@example.com")
        );
    }

    // GET /customers/{customerId} -> Returns a single JSON customer object
    @GetMapping("/{customerId}")
    public Customer getCustomerById(@PathVariable String customerId) {
        return new Customer(customerId, "John Doe", "john.doe@example.com");
    }

    // POST /customers -> Accepts a JSON body and returns the created customer object
    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        // Business logic to save customer...
        return customer;
    }

    // PUT /customers/{customerId} -> Accepts JSON body to update an existing customer
    @PutMapping("/{customerId}")
    public Customer updateCustomer(@PathVariable String customerId, @RequestBody Customer updatedCustomer) {
        updatedCustomer.setId(customerId);
        return updatedCustomer;
    }

    // DELETE /customers/{customerId} -> Returns a simple status/confirmation
    @DeleteMapping("/{customerId}")
    public String deleteCustomer(@PathVariable String customerId) {
        return "Customer with ID " + customerId + " deleted successfully";
    }
}
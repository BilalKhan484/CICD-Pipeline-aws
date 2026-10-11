package com.restapi;

import com.restapi.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomersController {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomersController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // GET /customers -> Fetch all records from PostgreSQL
    @GetMapping
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    // GET /customers/{customerId} -> Fetch a single record by ID
    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable String customerId) {
        return customerRepository.findById(customerId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /customers -> Persist a new customer
    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    // PUT /customers/{customerId} -> Update an existing record
    @PutMapping("/{customerId}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable String customerId, @RequestBody Customer updatedCustomer) {
        return customerRepository.findById(customerId)
                .map(existingCustomer -> {
                    existingCustomer.setName(updatedCustomer.getName());
                    existingCustomer.setEmail(updatedCustomer.getEmail());
                    Customer saved = customerRepository.save(existingCustomer);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /customers/{customerId} -> Delete a record by ID
    @DeleteMapping("/{customerId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable String customerId) {
        if (customerRepository.existsById(customerId)) {
            customerRepository.deleteById(customerId);
            return ResponseEntity.ok("Customer with ID " + customerId + " deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }
}

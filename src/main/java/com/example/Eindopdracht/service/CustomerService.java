package com.example.Eindopdracht.service;

import com.example.Eindopdracht.dto.CustomerDto;
import com.example.Eindopdracht.dto.CustomerInputDto;
import com.example.Eindopdracht.exceptions.DuplicatedEntryException;
import com.example.Eindopdracht.exceptions.RecordNotFoundException;
import com.example.Eindopdracht.model.Car;
import com.example.Eindopdracht.model.Customer;
import com.example.Eindopdracht.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomerService {

    private final CustomerRepository customerRepos;

    public CustomerService(CustomerRepository repos) {
        this.customerRepos = repos;
    }

    public CustomerDto createCustomer(CustomerInputDto dto) {
        if(customerRepos.existsByEmail(dto.getEmail())) {
            throw new DuplicatedEntryException("customer email already exists");
        }else {
            Customer customer = transferToCustomer(dto);
            customerRepos.save(customer);

            return transferToDto(customer);
        }
    }

    public CustomerDto updateCustomer(Long id, CustomerInputDto dto) {
        if (customerRepos.findById(id).isPresent()) {
            Customer customer = customerRepos.findById(id).get();

            Customer existingCustomer = customerRepos.findCustomerByEmail(dto.getEmail());

            if(existingCustomer != null && !existingCustomer.getId().equals(customer.getId())) {
                throw new DuplicatedEntryException("Email already exists");
            } else {
                Customer updatedCustomer = transferToCustomer(dto);
                updatedCustomer.setId(customer.getId());

                customerRepos.save(updatedCustomer);

                return transferToDto(updatedCustomer);
            }
        } else {
            throw new RecordNotFoundException("No customer found");
        }
    }

    public void deleteCustomer(Long id) {
        customerRepos.deleteById(id);
    }

    public List<CustomerDto> getAllCustomers() {
        List<Customer> customerList = customerRepos.findAll();
        List<CustomerDto> customerDtolist = new ArrayList<>();
        for (Customer customer : customerList) {
            CustomerDto dto = transferToDto(customer);
            customerDtolist.add(dto);
        }
        return customerDtolist;
    }

    public CustomerDto getOneCustomer(Long id) {
        Optional<Customer> customer = customerRepos.findById(id);
        if (customer.isPresent()) {
            Customer c = customer.get();
            return transferToDto(c);
        } else {
            throw new RecordNotFoundException("Customer not found");
        }
    }

    public CustomerDto transferToDto(Customer customer) {
        CustomerDto dto = new CustomerDto();
        dto.setEmail(customer.getEmail());
        dto.setFirstname(customer.getFirstname());
        dto.setLastname(customer.getLastname());
        dto.setTown(customer.getTown());
        dto.setStreetname(customer.getStreetname());
        dto.setZipcode(customer.getZipcode());
        dto.setCars(customer.getCars());

        return dto;
    }

    public Customer transferToCustomer(CustomerInputDto dto) {
        Customer customer = new Customer();
        customer.setEmail(dto.email);
        customer.setFirstname(dto.firstname);
        customer.setLastname(dto.lastname);
        customer.setTown(dto.town);
        customer.setStreetname(dto.streetname);
        customer.setZipcode(dto.zipcode);
        customer.setCars((Set<Car>) dto.getCars());

        return customer;
    }
}
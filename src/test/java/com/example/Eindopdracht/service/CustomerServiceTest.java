package com.example.Eindopdracht.service;

import com.example.Eindopdracht.dto.CustomerDto;
import com.example.Eindopdracht.dto.CustomerInputDto;
import com.example.Eindopdracht.exceptions.RecordNotFoundException;
import com.example.Eindopdracht.model.Customer;
import com.example.Eindopdracht.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CustomerServiceTest {

    @Mock
    CustomerRepository customerRepository;

    @InjectMocks
    CustomerService customerService;

    @Captor
    ArgumentCaptor<Customer> argumentCaptor;

    Customer customer1;
    Customer customer2;

    @BeforeEach
    void setUp() {
        customer1 = new Customer(1L, "test@test.nl","test","gebruiker","testweg12","teststad","1234AB");
        customer2 = new Customer(1L, "test@test2.nl","test2","gebruiker2","testweg124","teststad2","1234CD");
    }

    @Test
    void createCustomer() {
        CustomerInputDto dto = new CustomerInputDto(1L, "test@test.nl","test","gebruiker","testweg12","teststad","1234AB");

        when(customerRepository.save(customer1)).thenReturn(customer1);

        customerService.createCustomer(dto);
        verify(customerRepository, times(1)).save(argumentCaptor.capture());
        Customer customer = argumentCaptor.getValue();

        assertEquals(customer1.getEmail(), customer.getEmail());
        assertEquals(customer1.getFirstname(), customer.getFirstname());
        assertEquals(customer1.getLastname(), customer.getLastname());
        assertEquals(customer1.getStreetname(), customer.getStreetname());
        assertEquals(customer1.getTown(), customer.getTown());
        assertEquals(customer1.getZipcode(), customer.getZipcode());
    }

    @Test
    void updateCustomer() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer1));

        CustomerInputDto dto = new CustomerInputDto(1L, "test@test.nl","test","gebruiker","testweg12","teststad","1234AB");

        when(customerRepository.save(customerService.transferToCustomer(dto))).thenReturn(customer1);

        customerService.updateCustomer(1L,dto);

        verify(customerRepository, times(1)).save(argumentCaptor.capture());

        Customer captured = argumentCaptor.getValue();

        assertEquals(dto.getEmail(), captured.getEmail());
        assertEquals(dto.getFirstname(), captured.getFirstname());
        assertEquals(dto.getLastname(), captured.getLastname());
        assertEquals(dto.getStreetname(), captured.getStreetname());
        assertEquals(dto.getTown(), captured.getTown());
        assertEquals(dto.getZipcode(), captured.getZipcode());
    }

    @Test
    void deleteCustomer() {
        customerService.deleteCustomer(1L);

        verify(customerRepository).deleteById(1L);
    }

    @Test
    void getAllCustomers() {
        when(customerRepository.findAll()).thenReturn(List.of(customer1, customer2));

        List<Customer> customers = customerRepository.findAll();
        List<CustomerDto> dtos = customerService.getAllCustomers();

        assertEquals(customers.get(0).getEmail(), dtos.get(0).getEmail());
        assertEquals(customers.get(0).getFirstname(), dtos.get(0).getFirstname());
        assertEquals(customers.get(0).getLastname(), dtos.get(0).getLastname());
        assertEquals(customers.get(0).getStreetname(), dtos.get(0).getStreetname());
        assertEquals(customers.get(0).getTown(), dtos.get(0).getTown());
        assertEquals(customers.get(0).getZipcode(), dtos.get(0).getZipcode());
    }

    @Test
    void getCustomer() {
        Long id = 1L;
        when(customerRepository.findById(id)).thenReturn(Optional.of(customer2));

        Customer customer = customerRepository.findById(id).get();
        CustomerDto dto = customerService.getOneCustomer(id);

        assertEquals(customer.getEmail(), dto.getEmail());
        assertEquals(customer.getFirstname(), dto.getFirstname());
        assertEquals(customer.getLastname(), dto.getLastname());
        assertEquals(customer.getStreetname(), dto.getStreetname());
        assertEquals(customer.getTown(), dto.getTown());
        assertEquals(customer.getZipcode(), dto.getZipcode());
    }

    @Test
    void updateAppointmentThrowsExceptionTest() {
        assertThrows(RecordNotFoundException.class, () -> customerService.updateCustomer(1L, new CustomerInputDto(1L, "test@test.nl","test","gebruiker","testweg12","teststad","1234AB")));
    }

    @Test
    void getAppointmentThrowsExceptionTest() {
        assertThrows(RecordNotFoundException.class, () -> customerService.getOneCustomer(null));
    }
}

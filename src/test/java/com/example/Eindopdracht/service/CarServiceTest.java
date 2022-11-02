package com.example.Eindopdracht.service;

import com.example.Eindopdracht.dto.CarDto;
import com.example.Eindopdracht.dto.CarInputDto;
import com.example.Eindopdracht.exceptions.RecordNotFoundException;
import com.example.Eindopdracht.model.Car;
import com.example.Eindopdracht.model.Customer;
import com.example.Eindopdracht.repository.CarRepository;
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

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CarServiceTest {

    @Mock
    CarRepository carRepository;

    @Mock
    CustomerRepository customerRepository;

    @InjectMocks
    CarService carService;

    @Captor
    ArgumentCaptor<Car> argumentCaptor;

    Car car1;
    Car car2;

    Customer customer1;
    Customer customer2;

    @BeforeEach
    void setUp() {
        customer1 = new Customer(1L, "test@test.nl","test","gebruiker","testweg12","teststad","1234AB");
        customer2 = new Customer(1L, "test@test2.nl","test2","gebruiker2","testweg124","teststad2","1234CD");

        car1 = new Car(1L,"Ford escord","04-23G-G", customer1);
        car2 = new Car(1L,"BMW","04-23G-G", customer2);
    }

    @Test
    void createCar() {
        CarInputDto dto = new CarInputDto(1L,"Ford escord","04-23G-G", customer1);

        given(customerRepository.findById(1L)).willReturn(Optional.of(customer1));
        when(carRepository.save(car1)).thenReturn(car1);

        carService.createCar(dto);
        verify(carRepository, times(1)).save(argumentCaptor.capture());
        Car car = argumentCaptor.getValue();

        assertEquals(car1.getBrand(), car.getBrand());
        assertEquals(car1.getLicenseplate(), car.getLicenseplate());
    }

   @Test
    void updateCar() {
        when(carRepository.findById(1L)).thenReturn(Optional.of(car1));
        given(customerRepository.findById(1L)).willReturn(Optional.of(customer1));

        CarInputDto dto = new CarInputDto(1L,"Ford escord","04-23G-G", customer1);

        when(carRepository.save(carService.transferToCar(dto))).thenReturn(car1);

        carService.updateCar(1L, dto);

        verify(carRepository, times(1)).save(argumentCaptor.capture());

        Car captured = argumentCaptor.getValue();

        assertEquals(dto.getBrand(), captured.getBrand());
        assertEquals(dto.getLicenseplate(), captured.getLicenseplate());
    }

     @Test
    void deleteCar() {
        carService.deleteCar(1L);

        verify(carRepository).deleteById(1L);
    }


    @Test
    void getAllCars() {

        when(carRepository.findAll()).thenReturn(List.of(car1, car2));

        List<Car> cars = (List<Car>) carRepository.findAll();
        Collection<CarDto> dtos = carService.getAllCars();

        assertEquals(cars.get(0).getBrand(), dtos.stream().findFirst().get().getBrand());
        assertEquals(cars.get(0).getLicenseplate(), dtos.stream().findFirst().get().getLicenseplate());
    }

    @Test
    void getAppointment() {
        Long id = 1L;
        when(carRepository.findById(id)).thenReturn(Optional.of(car2));

        Car car = carRepository.findById(id).get();
        CarDto dto = carService.getCar(id);

        assertEquals(car.getBrand(), dto.getBrand());
        assertEquals(car.getLicenseplate(), dto.getLicenseplate());
    }

    @Test
    void updateCarThrowsExceptionTest() {
        assertThrows(RecordNotFoundException.class, () -> carService.updateCar(1L, new CarInputDto(1L, "Ford escord", "04-23G-G")));
    }

    @Test
    void updateCarThrowsNotFoundExceptionTest() {
        CarInputDto carInputDto = new CarInputDto(1L,"Ford escord","04-23G-G");
        assertThrows(RecordNotFoundException.class, () -> carService.updateCar(null,carInputDto));
    }

    @Test
    void getCarThrowsExceptionTest() {
        assertThrows(RecordNotFoundException.class, () -> carService.getCar(null));
    }

    @Test
    void updateCarThrowsExceptionForCustomerTest() {
        when(carRepository.findById(any())).thenReturn(Optional.of(car1));
        assertThrows(RecordNotFoundException.class, () -> carService.updateCar(3L,new CarInputDto(1L,"Test","Test", customer1)));
    }
}

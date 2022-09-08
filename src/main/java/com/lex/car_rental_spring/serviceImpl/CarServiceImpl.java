package com.lex.car_rental_spring.serviceImpl;

import com.lex.car_rental_spring.entity.Car;
import com.lex.car_rental_spring.entity.Location;
import com.lex.car_rental_spring.entity.dto.CarCustomerDTO;
import com.lex.car_rental_spring.entity.mapper.CarMapper;
import com.lex.car_rental_spring.exception.CarNotFoundException;
import com.lex.car_rental_spring.repository.CarRepository;
import com.lex.car_rental_spring.service.CarService;
import com.vaadin.flow.router.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class CarServiceImpl implements CarService {
    private CarRepository carRepository;
    private LocationServiceImpl locationService;
    private CarMapper carMapper;

    @Override
    public List<Car> listAvailableCars(Integer pageNo, Integer pageSize, String sortBy) {
        try {
            Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
            Page<Car> pagedResult = carRepository.findByRentedFalse(paging);
            return pagedResult.getContent();
        } catch (CarNotFoundException c) {
            System.out.println("Nie ma dostępnych samochodów." + c.getMessage());
        }
        return null;
    }

    @Override
    public List<Car> listRentedCars(Integer pageNo, Integer pageSize, String sortBy) {
        try {
            Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
            Page<Car> pagedResult = carRepository.findByRentedTrue(paging);
            if (!pagedResult.hasContent()) {
                throw new CarNotFoundException("Wszystkie samochody są dostępne");
            }
            return pagedResult.getContent();
        } catch (CarNotFoundException c) {
            System.out.println(c.getMessage());
        }
        return null;
    }

    @Override
    public List<Car> listAllCars(Integer pageNo, Integer pageSize, String sortBy) {
        try {
            Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
            Page<Car> pagedResult = carRepository.findAll(paging);
            return pagedResult.getContent();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Car getCarById(Long id) {
        return carRepository.findById(id).orElseThrow(() -> new NotFoundException("Nie znaleziono samochodu o podanym id."));
    }

    @Override
    public void saveCar(Car car) {
        carRepository.save(car);
    }

    @Override
    public void patchCar(Long id, Map<String, Object> patch) {
        Car existingCar = getCarById(id);
        patch.forEach((key, value) -> {
            if (ReflectionUtils.findField(CarCustomerDTO.class, key) != null) {
                Field field = ReflectionUtils.findField(Car.class, key);
                if (field != null) {
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, existingCar, value);
                }
            }
        });
        carRepository.save(existingCar);
    }

    @Override
    public void rentCar(Long id) throws CarNotFoundException {
            Map<String, Object> patch = new HashMap<>();
            patch.put("rented", true);
            patchCar(id, patch);
    }

    @Override
    public void returnCar(Long id, String city) throws CarNotFoundException {
        Location location =  locationService.getLocationByCity(city);
        location.setCity(city);
        Map<String, Object> patch = new HashMap<>();
        patch.put("rented", false);
        patch.put("location", location);
        patchCar(id, patch);
    }

    @Override
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }
}


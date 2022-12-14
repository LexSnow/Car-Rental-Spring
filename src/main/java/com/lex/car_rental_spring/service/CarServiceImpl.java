package com.lex.car_rental_spring.service;

import com.lex.car_rental_spring.entity.Car;
import com.lex.car_rental_spring.entity.DTO.CarDTO;
import com.lex.car_rental_spring.entity.History;
import com.lex.car_rental_spring.entity.Location;
import com.lex.car_rental_spring.exception.CarNotFoundException;
import com.lex.car_rental_spring.repository.CarRepository;
import com.vaadin.flow.router.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final LocationServiceImpl locationService;
    private HistoryServiceImpl historyService;


    @Override
    public List<Car> listAvailableCars(Integer pageNo, Integer pageSize, String sortBy) {
        try {
            Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
            Page<Car> pagedResult = carRepository.findByRentedFalse(paging);
            return pagedResult.getContent();
        } catch (CarNotFoundException c) {
            throw new RuntimeException("Nie ma dostępnych samochodów." + c.getMessage());
        }
    }

    @Override
    public List<Car> listRentedCars(Integer pageNo, Integer pageSize, String sortBy) {
        try {
            Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
            Page<Car> pagedResult = carRepository.findByRentedTrue(paging);
            return pagedResult.getContent();
        } catch (CarNotFoundException c) {
            throw new RuntimeException("Wszystkie samochody są dostępne." + c.getMessage());
        }
    }

    @Override
    public List<Car> listAllCars(Integer pageNo, Integer pageSize, String sortBy) {
        try {
            Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
            Page<Car> pagedResult = carRepository.findAll(paging);
            return pagedResult.getContent();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Car getCarById(Long id) {
        return carRepository.findById(id).orElseThrow(() -> new NotFoundException("Nie znaleziono samochodu o podanym id."));
    }

    @Override
    public void saveCar(Location location, String brand, String model, Integer manufacturedYear, Integer odometer) {
        Car car = new Car(location, brand, model, manufacturedYear, odometer);
        carRepository.save(car);
    }

    @Override
    public void patchCar(Long id, Map<String, Object> patch) {
        Car existingCar = getCarById(id);
        patch.forEach((key, value) -> {
            if (ReflectionUtils.findField(CarDTO.class, key) != null) {
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
        if (getCarById(id).getRented()) {
            throw new CarNotFoundException("Samochód jest niedostępny.");
        }
        Car car = getCarById(id);
        car.setRented(true);
        History history = new History(car.getOdometer());
        history.setStartOdometer(car.getOdometer());
        history.setCar(car);
        historyService.updateHistory(history);
        car.addToHistory(history);
        carRepository.save(car);
    }

    @Override
    public void returnCar(Long id, String city, Integer endOdometer) throws CarNotFoundException {
        if (!getCarById(id).getRented()) {
            throw new CarNotFoundException("Samochód nie jest aktualnie wypożyczony");
        } else {
            Location location;
            location = locationService.getLocationByCity(city);
            Date dueDate = new Date();
            History historyEntry = historyService.getHistoryByCarIdAndDueDateIsNull(id);
            Car car = getCarById(id);
            car.setRented(false);
            car.setLocation(location);
            carRepository.save(car);
            historyEntry.setDueDate(dueDate);
            historyEntry.setEndOdometer(endOdometer);
            historyService.updateHistory(historyEntry);
        }
    }

    @Override
    public void deleteCar(Long id) throws CarNotFoundException {
        if (getCarById(id) == null) {
            throw new CarNotFoundException("Samochód o podanym id nie istnieje.");
        }
        carRepository.deleteById(id);
    }
}


package com.lex.car_rental_spring.service;

import com.lex.car_rental_spring.entity.LocationEntity.LocationFinder;
import com.lex.car_rental_spring.entity.LocationEntity.Location;
import com.lex.car_rental_spring.exception.IncorrectRequestException;
import com.lex.car_rental_spring.exception.LocationNotFoundException;
import com.lex.car_rental_spring.repository.LocationRepository;
import com.lex.car_rental_spring.service.ServiceInterfaces.LocationService;
import com.vaadin.flow.router.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.adampolsa.mapservice.msg.response.GeocodingLocRespEntry;
import pl.adampolsa.mapservice.msg.response.GeocodingLocResponse;

import java.util.List;
@AllArgsConstructor
@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    @Override
    public Location getLocationByCity(String city) throws LocationNotFoundException {
        if (!locationRepository.existsByCity(city)) {
            throw new LocationNotFoundException("Podana lokalizacja nie istnieje.");
        }
        return locationRepository.findByCity(city);
    }

    @Override
    public Location getLocationById(Long id) {
        return locationRepository.findById(id).orElseThrow(() -> new NotFoundException("Nie znaleziono lokalizacji o id " + id));
    }

    @Override
    public void addLocation(String city) throws IncorrectRequestException {
        if (locationRepository.existsByCity(city)) {
            throw new IncorrectRequestException("Ta lokalizacja już istnieje.");
        }
        LocationFinder calculator = new LocationFinder();
        GeocodingLocResponse request;
        try {
            request = calculator.sendReqGetRes(city);
        } catch (Throwable e) {
            throw new RuntimeException(e.getMessage());
        }
        Location location = new Location(city);
        location.setLat(request.getEntries().stream().mapToDouble(GeocodingLocRespEntry::getLat).sum());
        location.setLon(request.getEntries().stream().mapToDouble(GeocodingLocRespEntry::getLon).sum());
        locationRepository.save(location);
    }

    @Override
    public List<Location> listAllLocations(Integer pageNo, Integer pageSize, String sortBy){
        try {
            Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
            Page<Location> pagedResult = locationRepository.findAll(paging);
            return pagedResult.getContent();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void deleteLocation(Long id) throws LocationNotFoundException {
        if (getLocationById(id) != null) {
            locationRepository.deleteById(id);
        } else {
            throw new RuntimeException("Lokalizacja o podanym id nie istnieje");
        }
    }
}

package com.lex.car_rental_spring.serviceImpl;

import com.lex.car_rental_spring.entity.LocationFinder;
import com.lex.car_rental_spring.entity.Location;
import com.lex.car_rental_spring.exception.LocationNotFoundException;
import com.lex.car_rental_spring.repository.LocationRepository;
import com.lex.car_rental_spring.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.adampolsa.mapservice.msg.response.GeocodingLocRespEntry;
import pl.adampolsa.mapservice.msg.response.GeocodingLocResponse;

import java.util.List;
import java.util.Optional;
@Service
public class LocationServiceImpl implements LocationService {
    @Autowired
    private LocationRepository locationRepository;

    @Override
    public Location getLocationByCity(String city) {
        return locationRepository.findByCity(city);
    }

    @Override
    public Optional<Location> getLocationById(Long id) throws LocationNotFoundException {
        return locationRepository.findById(id);
    }

    @Override
    public void addLocation(String city) throws Throwable {
        if(!locationRepository.existsByCity(city)){
            LocationFinder calculator = new LocationFinder();
            GeocodingLocResponse request = new GeocodingLocResponse();
            try {
                request = calculator.sendReqGetRes(city);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
            Location location = new Location(city);
            location.setLat(request.getEntries().stream().mapToDouble(GeocodingLocRespEntry::getLat).sum());
            location.setLon(request.getEntries().stream().mapToDouble(GeocodingLocRespEntry::getLon).sum());
            locationRepository.save(location);
        }
    }

    @Override
    public List<Location> listAllLocations(Integer pageNo, Integer pageSize, String sortBy) throws LocationNotFoundException{
        try {
            Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
            Page<Location> pagedResult = locationRepository.findAll(paging);
            return pagedResult.getContent();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

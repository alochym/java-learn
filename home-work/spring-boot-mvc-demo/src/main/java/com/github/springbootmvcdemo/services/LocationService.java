package com.github.springbootmvcdemo.services;

import org.springframework.stereotype.Service;

import com.github.springbootmvcdemo.exceptions.NotFoundException;
import com.github.springbootmvcdemo.models.Location;
import com.github.springbootmvcdemo.repository.LocationRepository;

import jakarta.transaction.Transactional;

@Service
// jakarta.persistence.TransactionRequiredException: Executing an update/delete query
@Transactional
public class LocationService {

    // Inject LocationRepository into LocationService.
    private LocationRepository repo;

    public LocationService(LocationRepository repo) {
        this.repo = repo;
    }

    // CRUD Operation START
    /*
     * Get Location Operation.
     * 
     * Input a code string.
     * Ouput a location if a code is present in database.
     */
    public Location get(String code) {
        return this.repo.findByCode(code);
    }

    /*
     * Create Location Operation.
     * 
     * Input a location is not in database.
     * Ouput a location is save in database.
     */ 
    public Location save(Location l) {
        /*
         * TODO
         * 
         * Check if a location is in database
         * Return a location already exist
         */ 
        return this.repo.save(l);
    }    

    /*
     * Update Location Operation.
     * 
     * Input a code string.
     * Ouput a location if a code is present in database.
     */
    public Location update(Location locationRequest) throws NotFoundException {
        /*
         * Get a code and then find it in database
         */
        String code = locationRequest.getCode();

        Location loc = this.repo.findByCode(code);

        /*
         * check if code is not found then throw Not Found exception.
         */
        if (loc == null) {
            String message = "No Location found with given code: " + code;
            throw new NotFoundException(message);
        }

        loc.setCountryCode(locationRequest.getCountryCode());
        loc.setCountryName(locationRequest.getCountryName());
        loc.setCityName(locationRequest.getCityName());
        loc.setRegionName(locationRequest.getRegionName());
        loc.setEnable(locationRequest.isEnable());

        return this.repo.save(loc);
    }

    /*
     * Delete Location Operation.
     * 
     * Input a code string.
     * Ouput a location if a code is present in database.
     */
    public void delete(String code) throws NotFoundException {
        /*
         * Get a code and then find it in database
         */
        Location loc = this.repo.findByCode(code);

        /*
         * check if code is not found then throw Not Found exception.
         */
        if (loc == null) {
            String message = "No Location found with given code: " + code;
            throw new NotFoundException(message);
        }

        this.repo.trashedByCode(code);
    }
}

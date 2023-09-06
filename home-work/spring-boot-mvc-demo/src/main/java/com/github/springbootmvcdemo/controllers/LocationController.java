package com.github.springbootmvcdemo.controllers;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.springbootmvcdemo.exceptions.NotFoundException;
import com.github.springbootmvcdemo.models.Location;
import com.github.springbootmvcdemo.services.LocationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/locations")
public class LocationController {
    // Inject LocationService into LocationController.
    private LocationService svc;

    public LocationController(LocationService svc) {
        this.svc = svc;
    }

    @PostMapping
    /*
     * @Valid must be present for Validation HTTP request data
     * 
     * @RequestBody must be present
     * HTTP Request data is bind to Location variable
     * 
     * Create Operation.
     * 
     * Input a location.
     * curl -H "content-type:application/json" -XPOST localhost:8080/v1/locations -d
     * '{"code": "GA_USA","city_name": "Georgia City","country_code":
     * "US","country_name": "United State of America","region_name": "GA","enable":
     * true,"trashed": false}' -vvv
     * curl -H "content-type:application/json" -XPOST localhost:8080/v1/locations
     * -d @location.json -vvv
     * 
     * Ouput 201 Created Response:
     * Headers
     * < HTTP/1.1 201
     * < Location: /v1/localtions/GA_USA
     * < Content-Type: application/json
     * < Transfer-Encoding: chunked.
     * Content Body
     * {
     * "code": "GA_USA",
     * "enable": true,
     * "city_name": "Georgia City",
     * "region_name": "GA",
     * "country_name": "United State of America",
     * "country_code": "US"
     * }
     * 
     */
    public ResponseEntity<Location> save(@RequestBody @Valid Location l) {
        Location result = this.svc.save(l);

        URI uri = URI.create("/v1/locations/" + result.getCode());

        // Response to client
        return ResponseEntity.created(uri).body(result);
    }

    @GetMapping("/{code}")
    /*
     * 
     * @PathParam("code") String code
     * HTTP Request param is bind to code variable
     * 
     * Get Location Operation.
     * 
     * Input a code string.
     * curl -XGET localhost:8080/v1/locations/GA_USA -vvv
     * 
     * Ouput 200 OK Response if a code is present in database.:
     * Headers
     * < HTTP/1.1 200
     * < Location: /v1/localtions/GA_USA
     * < Content-Type: application/json
     * < Transfer-Encoding: chunked.
     * Content Body
     * {
     * "code": "GA_USA",
     * "enable": true,
     * "city_name": "Georgia City",
     * "region_name": "GA",
     * "country_name": "United State of America",
     * "country_code": "US"
     * }
     * 
     */
    public ResponseEntity<Location> get(@PathVariable("code") String code) {
        Location result = this.svc.get(code);

        // check code is in database or not
        if (result == null) {
            return ResponseEntity.notFound().build();
        }

        // Response to client
        return ResponseEntity.ok(result);
    }

    @PutMapping
    /*
     * @Valid must be present for Validation HTTP request data
     * 
     * @RequestBody must be present
     * HTTP Request data is bind to Location variable
     * 
     * Update Operation.
     * 
     * Input a location.
     * curl -H "content-type:application/json" -XPUT localhost:8080/v1/locations -d
     * '{"code": "GA_USA","city_name": "Georgia City","country_code":
     * "US","country_name": "United State of America","region_name": "GA","enable":
     * true}' -vvv
     * curl -H "content-type:application/json" -XPUT localhost:8080/v1/locations
     * -d @location-update.json -vvv
     * 
     * Ouput 200 Ok Response if Location is found in database:
     * Headers
     * < HTTP/1.1 201
     * < Location: /v1/localtions/GA_USA
     * < Content-Type: application/json
     * < Transfer-Encoding: chunked.
     * Content Body
     * {
     * "code": "GA_USA",
     * "enable": true,
     * "city_name": "Georgia City",
     * "region_name": "GA",
     * "country_name": "United State of America",
     * "country_code": "US"
     * }
     */
    public ResponseEntity<Location> update(@RequestBody @Valid Location l) {
        try {
            Location result;
            result = this.svc.update(l);
            return ResponseEntity.ok(result);
        } catch (NotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{code}")
    /*
     * 
     * @PathParam("code") String code
     * HTTP Request param is bind to code variable    
     * 
     * Delete Operation.
     * 
     * Input a code.
     * curl -H "content-type:application/json" -XDELETE localhost:8080/v1/locations/GA_USA -vvv
     * 
     * Ouput 204 No Content Response if code is found in database:
     * Headers
     * < HTTP/1.1 204
     * < Location: /v1/localtions/GA_USA
     * < Content-Type: application/json
     * < Transfer-Encoding: chunked.
     * }
     */    
    public ResponseEntity<?> delete(@PathVariable("code") String code) {
        try {
            this.svc.delete(code);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }
}

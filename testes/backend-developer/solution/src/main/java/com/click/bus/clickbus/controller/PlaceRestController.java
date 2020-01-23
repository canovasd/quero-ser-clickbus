package com.click.bus.clickbus.controller;

import com.click.bus.clickbus.domain.Place;
import com.click.bus.clickbus.service.PlaceRegistryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("places")
public class PlaceRestController {

    @Autowired
    private PlaceRegistryService regService;

    @PostMapping()
    public ResponseEntity postPlace(@RequestParam @Nullable String name,
                                    @RequestParam(required = false) String slug,
                                    @RequestParam(required = false) String city,
                                    @RequestParam(required = false) String state) {
        this.regService.insertOrUpdate(name, slug, city, state);
        return new ResponseEntity(CREATED);
    }

    @GetMapping(produces = "application/json")
    public Collection<Place> listPlaces(@RequestParam(required = false) String filter) {
        return this.regService.getPlaces(filter);
    }

    @RequestMapping(path = "/id/{name}", produces = "application/json", method = GET)
    public Place getPlace(@PathVariable() String name) {
        return this.regService.getPlace(name);
    }

}
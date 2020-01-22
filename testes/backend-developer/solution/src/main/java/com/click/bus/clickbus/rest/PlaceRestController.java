package com.click.bus.clickbus.rest;

import com.click.bus.clickbus.actor.PlaceFilter;
import com.click.bus.clickbus.actor.PlaceRegistrer;
import com.click.bus.clickbus.domain.InMemoryDatabase;
import com.click.bus.clickbus.domain.Place;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("places")
public class PlaceRestController {

    @Resource
    private InMemoryDatabase db;

    @Resource
    private PlaceRegistrer registrer;

    @PostMapping("/register")
    public ResponseEntity postPlace(@RequestParam String name,
                                    @RequestParam(required = false) String slug,
                                    @RequestParam(required = false) String city,
                                    @RequestParam(required = false) String state) {
        this.registrer.insertOrUpdate(name, slug, city, state);
        return new ResponseEntity(CREATED);
    }

    @GetMapping(value = "/list", produces = "application/json")
    public Collection<Place> listPlaces(@RequestParam(required = false) String filter) {
        if (filter != null) {
            PlaceFilter placeFilter = new PlaceFilter(this.db);
            return placeFilter.filterBy(filter);
        }
        return this.db.getPlaces();
    }

    @RequestMapping(path = "/place/{name}", produces = "application/json", method = GET)
    public Place getPlace(@PathVariable String name) {
        return this.db.getPlace(name);
    }

}
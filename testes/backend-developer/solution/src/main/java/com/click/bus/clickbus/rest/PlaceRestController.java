package com.click.bus.clickbus.rest;

import com.click.bus.clickbus.domain.Place;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("places")
public class PlaceRestController {

    @GetMapping(value = "/list", produces = "application/json")
    public List<Place> listPlaces() {
        List<Place> places = new ArrayList<>();
        Place testPlace = new Place();
        testPlace.setName("Sede do ClickBus");
        places.add(testPlace);
        return places;
    }
}

package com.click.bus.clickbus.repository;

import com.click.bus.clickbus.domain.Place;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class InMemoryDatabase {

    private Map<String, Place> places = new HashMap<>();

    public Collection<Place> getPlaces() {
        return places.values();
    }

    public void addOrReplace(Place place) {
        this.places.put(place.getName(), place);
    }

    public Place getPlace(String name) {
        return this.places.get(name);
    }

    public boolean contains(String name) {
        return this.places.containsKey(name);
    }
}

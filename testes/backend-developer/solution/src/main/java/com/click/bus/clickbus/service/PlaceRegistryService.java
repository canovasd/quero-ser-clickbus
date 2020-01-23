package com.click.bus.clickbus.service;

import com.click.bus.clickbus.repository.InMemoryDatabase;
import com.click.bus.clickbus.domain.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Date;

import static org.springframework.util.StringUtils.isEmpty;

@Service
public class PlaceRegistryService {

    @Autowired
    private PlaceFilterService placeFilter;

    @Autowired
    private InMemoryDatabase db;

    public void insertOrUpdate(String name, String slug, String city, String state) {
        Place place = new Place();
        place.setName(name);
        place.setSlug(slug);
        place.setCity(city);
        place.setState(state);
        insertOrUpdate(place);
    }

    private void insertOrUpdate(Place place) {
        String name = place.getName();
        if (this.db.contains(name)) {
            place.setUpdatedAt(new Date());
            place.setCreatedAt(this.db.getPlace(name).getCreatedAt());
        } else {
            place.setCreatedAt(new Date());
        }
        this.db.addOrReplace(place);
    }

    public Collection<Place> getPlaces(String filter) {
        if(isEmpty(filter)) {
            return this.db.getPlaces();
        }
        return this.placeFilter.filterBy(filter);
    }

    public Place getPlace(String name) {
        return this.db.getPlace(name);
    }

    public InMemoryDatabase getDb() {
        return db;
    }

    public void setDb(InMemoryDatabase db) {
        this.db = db;
    }
}

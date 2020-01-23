package com.click.bus.clickbus.service;

import com.click.bus.clickbus.repository.InMemoryDatabase;
import com.click.bus.clickbus.domain.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlaceFilterService {

    @Autowired
    private InMemoryDatabase db;

    public PlaceFilterService(InMemoryDatabase db) {
        this.db = db;
    }

    public List<Place> filterBy(String filter) {
        List filteredList = new ArrayList();
        for (Place place : this.db.getPlaces()) {
            if (place.getName().contains(filter)) {
                filteredList.add(place);
            }
        }
        return filteredList;
    }

}

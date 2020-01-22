package com.click.bus.clickbus.actor;

import com.click.bus.clickbus.domain.InMemoryDatabase;
import com.click.bus.clickbus.domain.Place;

import java.util.ArrayList;
import java.util.List;

public class PlaceFilter {

    private InMemoryDatabase db;

    public PlaceFilter(InMemoryDatabase db) {
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

package com.click.bus.clickbus.service;

import com.click.bus.clickbus.domain.Place;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlaceFilterService {

    public List<Place> filterBy(String filter, List<Place> places) {
        List filteredList = new ArrayList();
        for (Place place : places) {
            if (place.getName().contains(filter)) {
                filteredList.add(place);
            }
        }
        return filteredList;
    }

}

package com.click.bus.clickbus.service;

import com.click.bus.clickbus.domain.Place;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlaceFilterServiceTest {

    private static PlaceFilterService placeFilterService;

    private static List<Place> places = new ArrayList<>();

    @BeforeAll
    public static void prepare() {
        places.add(createPlace("Name1"));
        places.add(createPlace("Name10"));
        places.add(createPlace("Name11"));

        placeFilterService = new PlaceFilterService();
    }

    @Test
    public void filterNone() {
        List<Place> result = placeFilterService.filterBy("RandomName", places);
        assertEquals(0, result.size());
    }

    @Test
    public void filterAll() {
        List<Place> result = placeFilterService.filterBy("Name1", places);
        assertEquals(3, result.size());
    }

    @Test
    public void filterExactly() {
        List<Place> result = placeFilterService.filterBy("Name11", places);
        assertEquals(1, result.size());
        assertEquals(result.get(0).getName(), "Name11");
    }

    private static Place createPlace(String nome) {
        Place place = new Place();
        place.setName(nome);
        return place;
    }
}

package com.click.bus.clickbus.service;

import com.click.bus.clickbus.repository.InMemoryDatabase;
import com.click.bus.clickbus.domain.Place;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlaceFilterServiceTest {

    private static PlaceFilterService placeFilterService;

    @BeforeAll
    public static void filter() {
        InMemoryDatabase db = new InMemoryDatabase();
        db.addOrReplace(createPlace("Name1"));
        db.addOrReplace(createPlace("Name10"));
        db.addOrReplace(createPlace("Name11"));
        placeFilterService = new PlaceFilterService(db);

        List<Place> result = placeFilterService.filterBy("Name1");
        assertEquals(result.size(), 3);
    }

    @Test
    public void filterNone() {
        List<Place> result = placeFilterService.filterBy("RandomName");
        assertEquals(0, result.size());
    }

    @Test
    public void filterAll() {
        List<Place> result = placeFilterService.filterBy("Name1");
        assertEquals(3, result.size());
    }

    @Test
    public void filterExactly() {
        List<Place> result = placeFilterService.filterBy("Name11");
        assertEquals(1, result.size());
        assertEquals(result.get(0).getName(), "Name11");
    }

    private static Place createPlace(String nome) {
        Place place = new Place();
        place.setName(nome);
        return place;
    }
}

package com.click.bus.clickbus.service;

import com.click.bus.clickbus.repository.InMemoryDatabase;
import com.click.bus.clickbus.domain.Place;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PlaceRegisterUpdateTest {


    private static final String NAME = "Name";

    private static final String NEW_SLUG = "AnotherSlug";

    private static final String NEW_CITY = "AnotherCity";

    private static final String NEW_STATE = "AnotherState";

    private static PlaceRegistryService registrer;

    private static InMemoryDatabase db;

    @BeforeAll
    public static void setup() {
        // given
        registrer = new PlaceRegistryService();
        db = new InMemoryDatabase();
        registrer.setDb(db);

        // when
        registrer.insertOrUpdate(NAME, "Slug", "City", "State");
        registrer.insertOrUpdate(NAME, NEW_SLUG, NEW_CITY, NEW_STATE);
    }

    @Test
    public void sameName() {
        //then
        Place place = db.getPlaces().iterator().next();
        assertEquals(NAME, place.getName());
    }

    @Test
    public void updatedSlug() {
        //then
        Place place = db.getPlaces().iterator().next();
        assertEquals(NEW_SLUG, place.getSlug());
    }

    @Test
    public void updatedCity() {
        //then
        Place place = db.getPlaces().iterator().next();
        assertEquals(NEW_CITY, place.getCity());
    }

    @Test
    public void updatedState() {
        //then
        Place place = db.getPlaces().iterator().next();
        assertEquals(NEW_STATE, place.getState());
    }

    @Test
    public void createAtSetted() {
        //then
        Place place = db.getPlaces().iterator().next();
        assertNotNull(place.getCreatedAt());
    }

    @Test
    public void updatedAtSetted() {
        //then
        Place place = db.getPlaces().iterator().next();
        assertNotNull(place.getUpdatedAt());
    }

}

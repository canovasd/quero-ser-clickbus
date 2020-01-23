package com.click.bus.clickbus.service;

import com.click.bus.clickbus.repository.InMemoryDatabase;
import com.click.bus.clickbus.domain.Place;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PlaceRegistrerInsertTest {

    private static final String NAME = "Name";

    private static final String SLUG = "Slug";

    private static final String CITY = "City";

    private static final String STATE = "State";

    private static PlaceRegistryService registrer;

    private static InMemoryDatabase db;

    @BeforeAll
    public static void setup() {
        // given
        registrer = new PlaceRegistryService();
        db = new InMemoryDatabase();
        registrer.setDb(db);

        // when
        registrer.insertOrUpdate(NAME, SLUG, CITY, STATE);
    }

    @Test
    public void correctName() {
        //then
        Place place = db.getPlaces().iterator().next();
        assertEquals(NAME, place.getName());
    }

    @Test
    public void correctSlug() {
        //then
        Place place = db.getPlaces().iterator().next();
        assertEquals(SLUG, place.getSlug());
    }

    @Test
    public void correctCity() {
        //then
        Place place = db.getPlaces().iterator().next();
        assertEquals(CITY, place.getCity());
    }

    @Test
    public void correctState() {
        //then
        Place place = db.getPlaces().iterator().next();
        assertEquals(STATE, place.getState());
    }

    @Test
    public void createAtSetted() {
        //then
        Place place = db.getPlaces().iterator().next();
        assertNotNull(place.getCreatedAt());
    }

    public void update() {
        //when
        registrer.insertOrUpdate("Name", "AnotherSlug", "AnotherCity", "AnotherState");

        //then
        Place place = db.getPlaces().iterator().next();

        assertEquals("Name", place.getName());
        assertEquals("AnotherSlug", place.getSlug());
        assertEquals("AnotherCity", place.getCity());
        assertEquals("AnotherState", place.getState());

        assertNotNull(place.getCity());
        assertNotNull(place.getUpdatedAt());
    }

}
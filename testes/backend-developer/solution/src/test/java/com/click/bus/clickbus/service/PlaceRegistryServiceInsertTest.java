package com.click.bus.clickbus.service;

import com.click.bus.clickbus.domain.Place;
import com.click.bus.clickbus.repository.PlaceRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PlaceRegistryServiceInsertTest {

    private static final String NAME = "Name";

    private static final String SLUG = "Slug";

    private static final String CITY = "City";

    private static final String STATE = "State";

    private static PlacesService registryService;

    private static PlaceRepository repo;

    private static Place result;

    @BeforeAll
    public static void setup() {
        // given
        registryService = new PlacesService();
        repo = Mockito.mock(PlaceRepository.class);
        registryService.setRepo(repo);

        Optional<Place> place = Optional.empty();
        Mockito.when(repo.findById(Mockito.anyString())).thenReturn(place);

        // when
        result = registryService.insertOrUpdate(NAME, SLUG, CITY, STATE);
    }

    @Test
    public void saved() {
        //then
        Mockito.verify(repo).save(Mockito.any(Place.class));
    }

    @Test
    public void correctName() {
        //then
        assertEquals(NAME, result.getName());
    }

    @Test
    public void correctSlug() {
        //then
        assertEquals(SLUG, result.getSlug());
    }

    @Test
    public void correctCity() {
        //then
        assertEquals(CITY, result.getCity());
    }

    @Test
    public void correctState() {
        //then
        assertEquals(STATE, result.getState());
    }

    @Test
    public void createAtSetted() {
        //then
        assertNotNull(result.getCreatedAt());
    }

}
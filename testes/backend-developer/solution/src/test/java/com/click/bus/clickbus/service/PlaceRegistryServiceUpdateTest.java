package com.click.bus.clickbus.service;

import com.click.bus.clickbus.domain.Place;
import com.click.bus.clickbus.repository.PlaceRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PlaceRegistryServiceUpdateTest {

    private static final String NAME = "Name";

    private static final String NEW_SLUG = "AnotherSlug";

    private static final String NEW_CITY = "AnotherCity";

    private static final String NEW_STATE = "AnotherState";

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
        registryService.insertOrUpdate(NAME, "Slug", "City", "State");

        Optional<Place> oldPlace = Optional.of(new Place());
        Mockito.when(repo.findById(Mockito.anyString())).thenReturn(oldPlace);
        result = registryService.insertOrUpdate(NAME, NEW_SLUG, NEW_CITY, NEW_STATE);
    }

    @Test
    public void sameName() {
        //then
        assertEquals(NAME, result.getName());
    }

    @Test
    public void updatedSlug() {
        //then
        assertEquals(NEW_SLUG, result.getSlug());
    }

    @Test
    public void updatedCity() {
        //then
        assertEquals(NEW_CITY, result.getCity());
    }

    @Test
    public void updatedState() {
        //then
        assertEquals(NEW_STATE, result.getState());
    }

    @Test
    public void updatedAtSetted() {
        //then
        assertNotNull(result.getUpdatedAt());
    }

}

package com.click.bus.clickbus.service;

import com.click.bus.clickbus.domain.Place;
import com.click.bus.clickbus.repository.PlaceRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PlacesServiceTest {

    private static final String NAME = "Name";

    private static final String SLUG = "Slug";

    private static final String CITY = "City";

    private static final String STATE = "State";

    private static final String NEW_SLUG = "AnotherSlug";

    private static final String NEW_CITY = "AnotherCity";

    private static final String NEW_STATE = "AnotherState";

    @Test
    public void insertion() {
        // given
        PlacesService registryService = new PlacesService();
        PlaceRepository repo = Mockito.mock(PlaceRepository.class);
        registryService.setRepo(repo);

        Optional<Place> place = Optional.empty();
        Mockito.when(repo.findById(Mockito.anyString())).thenReturn(place);

        // when
        Place result = registryService.insertOrUpdate(NAME, SLUG, CITY, STATE);

        //then
        Mockito.verify(repo).save(Mockito.any(Place.class));
        assertEquals(NAME, result.getName());
        assertEquals(SLUG, result.getSlug());
        assertEquals(CITY, result.getCity());
        assertEquals(STATE, result.getState());
        assertNotNull(result.getCreatedAt());
    }

    @Test
    public void update() {
        // given
        PlacesService registryService = new PlacesService();
        PlaceRepository repo = Mockito.mock(PlaceRepository.class);
        registryService.setRepo(repo);

        Optional<Place> place = Optional.empty();
        Mockito.when(repo.findById(Mockito.anyString())).thenReturn(place);

        // when
        registryService.insertOrUpdate(NAME, SLUG, CITY, STATE);

        Optional<Place> oldPlace = Optional.of(new Place());
        Mockito.when(repo.findById(Mockito.anyString())).thenReturn(oldPlace);
        Place result = registryService.insertOrUpdate(NAME, NEW_SLUG, NEW_CITY, NEW_STATE);

        //then
        Mockito.verify(repo, Mockito.times(2)).save(Mockito.any(Place.class));
        assertEquals(NAME, result.getName());
        assertEquals(NEW_SLUG, result.getSlug());
        assertEquals(NEW_CITY, result.getCity());
        assertEquals(NEW_STATE, result.getState());
        assertNotNull(result.getUpdatedAt());
    }

    @Test
    public void filter() {
        PlacesService placesService = new PlacesService();
        List<Place> places = new ArrayList<>();

        places.add(createPlace("Name1"));
        places.add(createPlace("Name10"));
        places.add(createPlace("Name11"));

        assertEquals(0, placesService.filterBy("RandomName", places).size());
        assertEquals(3, placesService.filterBy("Name1", places).size());
        assertEquals(1, placesService.filterBy("Name11", places).size());
    }

    private static Place createPlace(String nome) {
        Place place = new Place();
        place.setName(nome);
        return place;
    }

}
package com.click.bus.clickbus.service;

import com.click.bus.clickbus.domain.Place;
import com.click.bus.clickbus.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.springframework.util.StringUtils.isEmpty;

@Service
public class PlaceRegistryService {

    @Autowired
    private PlaceFilterService placeFilter;

    @Autowired
    private PlaceRepository repo;

    public Place insertOrUpdate(String name, String slug, String city, String state) {
        Place place = new Place();
        place.setName(name);
        place.setSlug(slug);
        place.setCity(city);
        place.setState(state);
        insertOrUpdate(place);
        return place;
    }

    private void insertOrUpdate(Place place) {
        String name = place.getName();
        Optional<Place> opt = this.repo.findById(name);
        if (opt.isPresent()) {
            place.setUpdatedAt(new Date());
            place.setCreatedAt(opt.get().getCreatedAt());
        } else {
            place.setCreatedAt(new Date());
        }
        this.repo.save(place);
    }

    public List<Place> getPlaces(String filter) {
        List<Place> places = new ArrayList<>();
        repo.findAll().forEach(places::add);
        if (isEmpty(filter)) {
            return places;
        }
        return this.placeFilter.filterBy(filter, places);
    }

    public Place getPlace(String name) {
        return this.repo.findById(name).orElse(null);
    }

    public PlaceRepository getRepo() {
        return repo;
    }

    public void setRepo(PlaceRepository repo) {
        this.repo = repo;
    }
}

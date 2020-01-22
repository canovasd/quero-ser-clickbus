package com.click.bus.clickbus.actor;

import com.click.bus.clickbus.domain.InMemoryDatabase;
import com.click.bus.clickbus.domain.Place;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
public class PlaceRegistrer {

    @Resource
    private InMemoryDatabase db;

    public void insertOrUpdate(String name, String slug, String city, String state) {
        Place place = new Place();
        place.setName(name);
        place.setSlug(slug);
        place.setCity(city);
        place.setState(state);
        insertOrUpdate(place);
    }

    private void insertOrUpdate(Place place) {
        String name = place.getName();
        if (this.db.contains(name)) {
            place.setUpdatedAt(new Date());
            place.setCreatedAt(this.db.getPlace(name).getCreatedAt());
        } else {
            place.setCreatedAt(new Date());
        }
        this.db.addOrReplace(place);
    }

    public InMemoryDatabase getDb() {
        return db;
    }

    public void setDb(InMemoryDatabase db) {
        this.db = db;
    }
}

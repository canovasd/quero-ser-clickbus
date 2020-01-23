package com.click.bus.clickbus.controller;

import com.click.bus.clickbus.ClickbusApplication;
import com.click.bus.clickbus.domain.Place;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = ClickbusApplication.class)
@WebAppConfiguration
public class PlaceRestControllerTest {

    private static final String PLACE_NAME = "Place 1";

    private static final String CITY_NAME = "City 1";

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void createPlace() throws Exception {
        String uri = "/places/";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .param("name", PLACE_NAME)
                .param("city", CITY_NAME)
        ).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
    }

    @Test
    public void getPlaceList() throws Exception {
        this.createPlace();

        String uri = "/places/";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Place[] placeList = this.mapFromJson(content, Place[].class);
        Assertions.assertTrue(placeList.length > 0);
        assertEquals(PLACE_NAME, placeList[0].getName());
    }

    @Test
    public void getExactPlace() throws Exception {
        this.createPlace();

        String uri = "/places/id/" + PLACE_NAME;
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Place place = this.mapFromJson(content, Place.class);
        assertEquals(CITY_NAME, place.getCity());
    }

    private <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

}
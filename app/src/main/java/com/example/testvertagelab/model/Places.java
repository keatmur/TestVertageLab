package com.example.testvertagelab.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Places {

    private List<Marker> places = null;

    private Map<String, Object> additionalProperties = new HashMap<String, Object>();


    public List<Marker> getPlaces() {
        return places;
    }


    public void setPlaces(List<Marker> places) {
        this.places = places;
    }


    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }


    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}


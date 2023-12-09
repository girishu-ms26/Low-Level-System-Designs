package com.bms.service;

import com.bms.model.City;
import com.bms.model.Theatre;
import com.bms.provider.DataProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TheatreService {

    public Map<City,List<Theatre>> cityTheatreMap;
    private final DataProvider provider;
    private final CityService cityService;

    public TheatreService(DataProvider provider, CityService cityService) {
        this.provider = provider;
        this.cityService = cityService;
        this.cityTheatreMap = this.provider.cityTheatreMap;
    }
    public List<Theatre> getAllTheatres() {
       List<Theatre> theatres = new ArrayList<>();
       for(Map.Entry<City,List<Theatre>> entry : cityTheatreMap.entrySet()) {
           theatres.addAll(entry.getValue());
       }
       return theatres;
    }

    public List<Theatre> theatresBasedOnCity(String city) {
        return cityTheatreMap.get(cityService.getCityByName(city));
    }

}

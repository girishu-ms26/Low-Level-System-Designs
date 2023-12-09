package com.bms.service;

import com.bms.model.City;
import com.bms.provider.DataProvider;

import java.util.ArrayList;
import java.util.List;

public class CityService {
    public List<City> cityList;
    public final DataProvider provider;
     public CityService(DataProvider provider) {
         this.provider = provider;
         this.cityList = provider.cityList;
     }

     public List<City> getAllCities() {
         return this.cityList;
     }

     public City getCityByName(String city) {
         for (City cities : cityList) {
             if(cities.getCityName() == city) {
                 return cities;
             }
         }
         return null;
     }
}

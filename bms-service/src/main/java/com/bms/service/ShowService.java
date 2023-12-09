package com.bms.service;

import com.bms.model.Movie;
import com.bms.model.Show;
import com.bms.provider.DataProvider;

import java.util.List;
import java.util.Map;

public class ShowService {

    private final DataProvider provider;

    public Map<Movie,List<Show>> movieShowMap;

    public ShowService(DataProvider provider) {
        this.provider = provider;
        this.movieShowMap=this.provider.movieShowMap;
    }


    public Show getShowById(int showId) throws Exception {
        for(Map.Entry<Movie,List<Show>> entry : movieShowMap.entrySet()) {
            for(int i=0;i<entry.getValue().size();i++){
                if(entry.getValue().get(i).getShowId() == showId) {
                    return entry.getValue().get(i);
                }
            }
        }
        throw new Exception("Show not found");
    }
}

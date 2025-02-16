package org.test.crudimage.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.test.crudimage.entity.CatImage;

import java.util.Arrays;
import java.util.List;

@Service
public class CatAPIService {
    private final RestTemplate restTemplate = new RestTemplate();

    public List<CatImage> fetchKucingFromAPI() {
        String API_URL = "https://api.thecatapi.com/v1/images/search?limit=10";
        CatImage[] catArray = restTemplate.getForObject(API_URL, CatImage[].class);
        return Arrays.asList(catArray);
    }
}

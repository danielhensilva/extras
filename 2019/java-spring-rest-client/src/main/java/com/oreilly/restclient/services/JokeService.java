package com.oreilly.restclient.services;

import com.oreilly.restclient.json.JokeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class JokeService {

    private static final String BASE = "http://api.icndb.com/jokes/random?limitTo=[nerdy]";

    private final RestTemplate restTemplate;

    @Autowired
    public JokeService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public String getJoke(String first, String last) {
        String url = new StringBuilder(BASE)
                .append("&firstName=").append(first)
                .append("&lastName=").append(last)
                .toString();

        JokeResponse response = this.restTemplate.getForObject(url, JokeResponse.class);
        if (response == null || response.getValue() == null)
            return "";

        return response.getValue().getJoke();
    }

}

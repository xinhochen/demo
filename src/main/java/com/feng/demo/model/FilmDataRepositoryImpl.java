package com.feng.demo.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class FilmDataRepositoryImpl implements FilmDataRepository {
    private static final Logger logger = LogManager.getLogger(FilmDataRepositoryImpl.class.getName());

    private final List<FilmData> filmDataList = new ArrayList<FilmData>();
    private final List<String> titleList = new ArrayList<String>();
    private final List<String> locationsList = new ArrayList<String>();
    private final List<String> directorList = new ArrayList<String>();
    private final List<String> writerList = new ArrayList<String>();


    /**
     * Fetch film data from the DataSF, and put it to the filmDataList
     */
    @PostConstruct
    private void init() {
        // Convert url string to URI Object for HTTP Request
        final String uriString = "https://data.sfgov.org/resource/wwmu-gmzc.json";
        UriComponents uriComponents = UriComponentsBuilder.fromUriString(uriString).build().encode();
        URI uri = uriComponents.toUri();

        RestTemplate template = new RestTemplate();

        // attach the app-token to the HTTP request
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-App-Token", "ZJoQPCSmAdYV12NuABNoGtHMD");
        headers.set("Content-Type", "application/json");
        HttpEntity<?> requestEntity = new HttpEntity<Object>(headers);

        HttpEntity<String> response = template.exchange(uri, HttpMethod.GET, requestEntity, String.class);
        parseJSON(response.getBody());
    }

    /**
     * Parse JSON String and add to the repository
     * @param data JSON String from the DataSF
     */
    private void parseJSON(String data) {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(data);
            JSONArray jsonArray = (JSONArray) obj;

            for (Object o : jsonArray) {
                JSONObject jsonItem = (JSONObject) o;

                FilmData filmData = assembleFilmData(jsonItem);
                putFilmData(filmData);
            }
        } catch (ParseException e) {
            logger.debug("getFilmData exception", e);
        }
    }

    private FilmData assembleFilmData(JSONObject jsonObject) {
        FilmData data = new FilmData();
        data.setTitle((String) jsonObject.get("title"));
        data.setReleaseYear(Integer.parseInt((String) jsonObject.get("release_year")));
        data.setLocations((String) jsonObject.get("locations"));
        data.setFunFacts((String) jsonObject.get("fun_facts"));
        data.setProductionCompany((String) jsonObject.get("product_company"));
        data.setDistributor((String) jsonObject.get("distributor"));
        data.setDirector((String) jsonObject.get("director"));
        data.setWriter((String) jsonObject.get("writer"));
        data.setActor1((String) jsonObject.get("actor1"));
        data.setActor2((String) jsonObject.get("actor2"));
        data.setActor3((String) jsonObject.get("actor3"));
        return data;
    }

    public void putFilmData(FilmData data) {
        filmDataList.add(data);

        if (!titleList.contains(data.getTitle())) {
            titleList.add(data.getTitle());
        }

        if (!locationsList.contains(data.getLocations())) {
            locationsList.add(data.getLocations());
        }

        if (!directorList.contains(data.getDirector())) {
            directorList.add(data.getLocations());
        }

        if (!writerList.contains(data.getWriter())) {
            writerList.add(data.getWriter());
        }
    }

    public List getLocationByTitle(String title) {
        List<String> locationList = new ArrayList<String>();

        for (FilmData data : filmDataList) {
            if (title.equalsIgnoreCase(data.getTitle())) {
                locationList.add(data.getLocations());
            }
        }
        return locationList;
    }

    public List getTitleList() {
        return titleList;
    }

    public List getLocationsList() {
        return locationsList;
    }

    public List getDirectorList() {
        return directorList;
    }

    public List getWriterList() {
        return writerList;
    }
}

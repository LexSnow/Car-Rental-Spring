package com.lex.car_rental_spring.entity;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.adampolsa.mapservice.msg.response.GeocodingLocRespEntry;
import pl.adampolsa.mapservice.msg.response.GeocodingLocResponse;
import pl.adampolsa.mapservice.msg.response.GeocodingDistanceResponse;
import pl.adampolsa.mapservice.msg.request.GeoCodingLocRequest;
import pl.adampolsa.mapservice.msg.request.GeoCodingDistanceRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class DistanceCalculator {
    public GeoCodingLocRequest createLocReq(String city) {
        GeoCodingLocRequest locRequest = new GeoCodingLocRequest();
        locRequest.setUseNominatim(true);
        locRequest.setAddress(city);
        locRequest.setLimit(1);
        return locRequest;
    }

    public GeocodingLocResponse sendReqGetRes(String city) throws Throwable {
        ObjectMapper mapper = new ObjectMapper();
        GeocodingLocResponse locResponse;
        URL url = new URL("http://10.10.10.83:8080/");
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Accept", "application/json");
        http.setRequestProperty("Content-type", "application/json");
        byte[] out = mapper.writeValueAsBytes(createLocReq(city));
        OutputStream stream = http.getOutputStream();
        stream.write(out);
        BufferedReader output = new BufferedReader(new InputStreamReader(http.getInputStream(), StandardCharsets.UTF_8));
        locResponse = mapper.readValue(output, GeocodingLocResponse.class);
        http.disconnect();
        return locResponse;
    }

    public GeoCodingDistanceRequest createDistReq(String city1, String city2) throws Throwable {
        GeoCodingDistanceRequest distanceRequest = new GeoCodingDistanceRequest();
        distanceRequest.addPoint(sendReqGetRes(city1).getEntries().stream().mapToDouble(GeocodingLocRespEntry::getLat).sum(), sendReqGetRes(city1).getEntries().stream().mapToDouble(GeocodingLocRespEntry::getLon).sum());
        distanceRequest.addPoint(sendReqGetRes(city2).getEntries().stream().mapToDouble(GeocodingLocRespEntry::getLat).sum(), sendReqGetRes(city2).getEntries().stream().mapToDouble(GeocodingLocRespEntry::getLon).sum());
        return distanceRequest;
    }

    public Integer calculateDistance(String city1, String city2) throws Throwable {
        int distance;
        GeocodingDistanceResponse distanceResponse;
        ObjectMapper mapper = new ObjectMapper();
        try {
            URL url = new URL("http://10.10.10.83:8080/");
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("POST");
            http.setDoOutput(true);
            http.setRequestProperty("Accept", "application/json");
            http.setRequestProperty("Content-type", "application/json");
            byte[] out = mapper.writeValueAsBytes(createDistReq(city1, city2));
            String string = new String(out);
            OutputStream stream = http.getOutputStream();
            stream.write(out);
            BufferedReader output = new BufferedReader(new InputStreamReader(http.getInputStream(), StandardCharsets.UTF_8));
            distanceResponse = mapper.readValue(output, GeocodingDistanceResponse.class);
            http.disconnect();
            distance =(Integer) distanceResponse.getDistanceKm().intValue();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return distance;
    }
}


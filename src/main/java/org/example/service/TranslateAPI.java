package org.example.service;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import org.example.entity.TranslateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component("translate")
public class TranslateAPI {

    @Value("${auth_token}")
    private String TOKEN;

    String TRANSLATE_SERVICE_URL = "https://developers.lingvolive.com/";

    private final RestTemplate restTemplate;

    private final Map<Languages, Integer> languages = new HashMap<>();

    {
        languages.put(Languages.ENG, 1033);
        languages.put(Languages.RU, 1049);
    }

    @Autowired
    public TranslateAPI(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String translate(IReader iReader, Languages fromLang, Languages toLang) {

        int srcLang = languages.get(fromLang);
        int dstLang = languages.get(toLang);

        String text = iReader.getWord();

        String MAIN_URL = getParametrizedUrlForTranslationAPI(text, srcLang, dstLang, false);
        HttpEntity<String> entity = getEntity("Authorization", TOKEN);
        ResponseEntity<String> responseEntity = null;

        try {
            responseEntity = restTemplate.exchange(MAIN_URL, HttpMethod.GET, entity,
                    new ParameterizedTypeReference<>() {
                    });
        } catch (HttpClientErrorException exception) {
            return "No translations found for text {"+text+"}";
        }

        String jsonString = responseEntity.getBody();

        ObjectMapper mapper = new ObjectMapper();

        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.setVisibility(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));

        TranslateResponse translateResponse = null;
        try {
            translateResponse = mapper.readValue(jsonString, TranslateResponse.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        String resultTranslatedWord = translateResponse.getTranslation().getTranslation();
        Buffer.getInstance().setValue(resultTranslatedWord);
        return resultTranslatedWord;
    }

    private String getParametrizedUrlForTranslationAPI(String text, int srcLang, int dstLang, boolean isCaseSensitive) {
        StringBuilder stringBuilder = new StringBuilder("api/v1/Minicard?text=");

        stringBuilder
                .append(text).append("&srcLang=").append(srcLang).append("&dstLang=")
                .append(dstLang).append("&isCaseSensitive=").append(isCaseSensitive);

        String API_URL = stringBuilder.toString();
        return TRANSLATE_SERVICE_URL + API_URL;
    }

    private String getAuthorizationToken() {
        String API_KEY = "Basic ZWIxYWE4ZGMtMzRlMy00ZDllLTk3MTgtNzBmYzg1NzkzMjdjOjc2OGNkNzcwZDhjYTQyNDk5ZGYyYWIzYzY3ZDRhNGUy";
        String URL = TRANSLATE_SERVICE_URL + "api/v1.1/authenticate";

        HttpEntity<String> entity = getEntity("Authorization", API_KEY);

        ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.POST, entity,
                new ParameterizedTypeReference<>() {
                });

        return "Bearer " + responseEntity.getBody();
    }

    private HttpEntity<String> getEntity(String key, String value) {
        MultiValueMap<String, String> multiValueMap = new HttpHeaders();
        multiValueMap.add(key, value);

        HttpHeaders headers = new HttpHeaders(multiValueMap);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        return entity;
    }

}

package es.amm.interfaces;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class RestTemplateHelper {


    public static HttpEntity<String> getHttpEntity (String json){
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        final HttpEntity<String> request = new HttpEntity<>(json, headers);
        return request;
    }


}

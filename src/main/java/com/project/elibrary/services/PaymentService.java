package com.project.elibrary.services;

import java.util.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.project.elibrary.models.pay;

@Service
public class PaymentService {
    private RestTemplate restTemplate;
    private String baseURL = "http://localhost:8060/library/Pay";

    public PaymentService() {
        this.restTemplate = new RestTemplate();
    }

    public List<pay> findAll() {
        return this.restTemplate.exchange(baseURL, HttpMethod.GET, null, new ParameterizedTypeReference<List<pay>>() {
        }).getBody();
    }

    public void save(pay pay) {
        String url = baseURL;
        if (pay.getId() == null) {
            this.restTemplate.postForObject(url, pay, pay.class);
        } else {
            // url += "/" + post.getId();
            // HttpEntity<Post> reqEntity = new HttpEntity<Post>(post);
            // this.restTemplate.put(url, reqEntity);
        }

    }

    public pay findById(long payId) {
        String Url = baseURL + "/" + payId;
        return restTemplate.getForObject(baseURL, pay.class);

    }
}

package com.entelgy.reto.app.service.impl;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.entelgy.reto.app.domain.Reto;
import com.entelgy.reto.app.domain.Root;
import com.entelgy.reto.app.service.RetoService;

@Service
public class RetoServiceImpl implements RetoService{
	
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${entelgy.metodoget.url}")
	String url;
	
	
	public Reto refactorizar() throws Exception {
		Reto reto = new Reto();
    	try {
        	URI uri = new URI(url);

    		HttpHeaders headers = new HttpHeaders();
    		headers.setContentType(MediaType.APPLICATION_JSON);
    		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    		headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

    		HttpEntity<Root> request = new HttpEntity<Root>(null, headers);
    		ResponseEntity<Root> result = restTemplate.exchange(uri, HttpMethod.GET, request, Root.class);

        	List<String> resp = result.getBody().getData().stream().map(x->(String.valueOf(x.getId())+"|"+x.getLast_name()+"|"+x.getEmail())).collect(Collectors.toList());
        	reto.setData(resp);
    		return reto;
    	}catch(Exception e) {
    		throw e;
    	}

	}

}

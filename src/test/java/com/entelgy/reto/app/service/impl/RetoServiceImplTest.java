package com.entelgy.reto.app.service.impl;

import static org.junit.Assert.assertTrue;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;
import static org.mockito.Mockito.*;

import com.entelgy.reto.app.domain.Datum;
import com.entelgy.reto.app.domain.Reto;
import com.entelgy.reto.app.domain.Root;

@RunWith(MockitoJUnitRunner.class)
public class RetoServiceImplTest extends MockHttpSession{

	@InjectMocks
	RetoServiceImpl retoService;
	
	@Mock
	RestTemplate restTemplate;
	
	
	@Before
	public void init() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void testRefactorizar() throws Exception {
		
		ReflectionTestUtils.setField(retoService, "url", "https://reqres.in/api/users");

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		requestHeaders.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

		HttpEntity<Root> requestEntity = new HttpEntity<Root>(null, requestHeaders);
		
		 Root root = new Root();
		 root.setData(dataenduro());
	        //define the entity you want the exchange to return
	        ResponseEntity<Root> myEntity = new ResponseEntity<Root>(root, HttpStatus.ACCEPTED);
	        Mockito.when(restTemplate.exchange(
	            eq(new URI("https://reqres.in/api/users")),
	            eq(HttpMethod.GET),
	            eq(requestEntity),
	            eq(Root.class))
	        ).thenReturn(myEntity);

	        Reto res = retoService.refactorizar();
	        assertTrue(res.getData() != null);
	}
	
	
	
	private List<Datum> dataenduro(){
		List<Datum> ls = new ArrayList<>();
		Datum obj = new Datum();
		obj.setId(1);obj.setEmail("prueba@gmail.com");obj.setFirst_name("Juan");obj.setLast_name("Perez");obj.setAvatar("Juan2");
		ls.add(obj);
		return ls;
	}
}

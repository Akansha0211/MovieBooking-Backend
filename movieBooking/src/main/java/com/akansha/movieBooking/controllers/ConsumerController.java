package com.akansha.movieBooking.controllers;

import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.akansha.movieBooking.entities.UserDTO;

@RestController
@RequestMapping("call/consumer")
@CrossOrigin(origins = "*")
public class ConsumerController {
	
	@PostMapping(value="/login", consumes =MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> consumeLogin(@RequestBody UserDTO userDto) throws RestClientException, Exception{
		
		String baseUrl = "http://localhost:8084/auth/v1/login";
		
		//"http://ec2-34-220-116-59.us-west-2.compute.amazonaws.com:8084/auth/v1/login";
		// ec2-34-220-116-59.us-west-2.compute.amazonaws.com
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Map<String,String>> result = null;
		try {
			result = restTemplate.exchange(
					baseUrl, 
					HttpMethod.POST, 
					getHeaders(userDto), 
					new ParameterizedTypeReference<Map<String,String>>(){}
					);
		}
		catch(RestClientException e) {
			return new ResponseEntity<String>("Login was not successful", HttpStatus.UNAUTHORIZED);
		}
		
		return new ResponseEntity<Map<String,String>>(result.getBody(), HttpStatus.OK);
	}
	
	private static HttpEntity<UserDTO> getHeaders(UserDTO userDto){
		HttpHeaders header = new HttpHeaders();
		header.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		header.set("Accept", MediaType.APPLICATION_JSON_VALUE);
	
		return new HttpEntity<UserDTO>(userDto, header);
	}

}

package com.arun.consumer.emp.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.arun.consumer.emp.model.Employee;

@RestController
@RequestMapping("/employee-consumer")
public class EmployeeConsumeController {
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@GetMapping("getAllEmployeesEurekaService")
	public List<Employee> getAllEmployeesEurekaService() throws RestClientException, IOException {
		
		List<ServiceInstance> instances=discoveryClient.getInstances("restapi-producer");
		ServiceInstance serviceInstance=instances.get(0);		
		String baseUrl=serviceInstance.getUri().toString();
		baseUrl = baseUrl+"/employee/getAllEmployees";
		System.out.println("base url :"+baseUrl);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<Employee>> response = restTemplate.exchange(baseUrl,HttpMethod.GET, getHeaders(), new ParameterizedTypeReference<List<Employee>>() {});
		System.out.println(response.getBody());
		return response.getBody();
	}
	
	@GetMapping("getAllEmployees")
	public List<Employee> getAllEmployees() throws RestClientException, IOException {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<Employee>> response = restTemplate.exchange("http://localhost:7070/employee/getAllEmployees",HttpMethod.GET, getHeaders(), new ParameterizedTypeReference<List<Employee>>() {});
		System.out.println(response.getBody());
		return response.getBody();
	}
	
	private static HttpEntity<?> getHeaders() throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<>(headers);
	}
}

package com.example.PostmanTests;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
@RestController
public class PostmanTestsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostmanTestsApplication.class, args);
	}



	@GetMapping("/hello")
	public String hello() {
		return "Hello World!";
	}

	@GetMapping("/customHeader")
	ResponseEntity<String> customHeader() {
		return new ResponseEntity<>(
						"Year of birth cannot be in the future",
						HttpStatus.BAD_REQUEST);
	}

}
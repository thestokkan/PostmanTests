package com.example.PostmanTests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
public class TeapotController {

  @GetMapping("/helloTeapot")
  public String helloTeapot() {
    return HttpStatus.I_AM_A_TEAPOT.getReasonPhrase();
  }
}
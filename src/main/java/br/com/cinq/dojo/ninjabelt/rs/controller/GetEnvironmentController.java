package br.com.cinq.dojo.ninjabelt.rs.controller;

import br.com.cinq.dojo.ninjabelt.rs.properties.EnvironmentProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetEnvironmentController {


  private final EnvironmentProperties environmentProperties;

  public GetEnvironmentController(
      final EnvironmentProperties environmentProperties) {
    this.environmentProperties = environmentProperties;
  }

  @RequestMapping("/environment")
  public ResponseEntity<EnvironmentProperties> getEnvironment() {
    return ResponseEntity.ok(environmentProperties);
  }
}
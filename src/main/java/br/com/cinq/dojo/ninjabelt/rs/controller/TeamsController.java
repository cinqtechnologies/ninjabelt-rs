package br.com.cinq.dojo.ninjabelt.rs.controller;

import br.com.cinq.dojo.ninjabelt.rs.properties.EnvironmentProperties;
import br.com.cinq.dojo.ninjabelt.rs.response.ListOfTeams;
import br.com.cinq.dojo.ninjabelt.rs.service.TeamService;
import br.com.cinq.dojo.ninjabelt.rs.transform.TeamsTransformer;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/team")
public class TeamsController {


  private final EnvironmentProperties environmentProperties;

  private final TeamService teamService;

  private final TeamsTransformer teamsTransformer;

  public TeamsController(
      final EnvironmentProperties environmentProperties,
      final TeamService teamService,
      final TeamsTransformer teamsTransformer) {
    this.environmentProperties = environmentProperties;
    this.teamService = teamService;
    this.teamsTransformer = teamsTransformer;
  }

  @RequestMapping(
      value = "/list",
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE
  )
  public ResponseEntity<ListOfTeams> getTeams() {
    final ListOfTeams listOfTeams = teamsTransformer.transform(teamService.getAllTeams());
    return ResponseEntity.ok(listOfTeams);
  }
}
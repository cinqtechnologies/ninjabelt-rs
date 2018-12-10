package br.com.cinq.dojo.ninjabelt.rs.controller;

import br.com.cinq.dojo.ninjabelt.rs.response.ListOfBelts;
import br.com.cinq.dojo.ninjabelt.rs.service.BeltsService;
import br.com.cinq.dojo.ninjabelt.rs.transform.BeltsTransformer;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/team/belts")
public class BeltsController {

  private final BeltsService beltsService;

  private final BeltsTransformer beltsTransformer;


  public BeltsController(
      final BeltsService beltsService,
      final BeltsTransformer beltsTransformer) {
    this.beltsService = beltsService;
    this.beltsTransformer = beltsTransformer;
  }

  @RequestMapping(
      value = "/presenters",
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE
  )
  public ResponseEntity<ListOfBelts> getBelts() {
    final ListOfBelts presentersBelts = beltsTransformer
        .transform(beltsService.getBeltForTeam("presenters"));
    if(presentersBelts == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(presentersBelts);
  }

  @RequestMapping(
      value = "/{teamName}/{beltColor}",
      method = RequestMethod.PUT
  )
  public ResponseEntity<Void> addBeltToTeam(
      final @PathVariable("teamName") String teamName,
      final @PathVariable("beltColor") String beltColor
  ) {
    if (beltsService.addBeltToTeam(teamName, beltColor)) {
      return ResponseEntity.ok().build();
    }
    return ResponseEntity.noContent().build();
  }
}
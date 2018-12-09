package br.com.cinq.dojo.ninjabelt.rs.service;

import br.com.cinq.dojo.ninjabelt.rs.model.Belt;
import br.com.cinq.dojo.ninjabelt.rs.model.ListOfBelts;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class BeltsService {

  private final MongoTemplate template;

  public BeltsService(final MongoTemplate template) {
    this.template = template;
  }

  @PostConstruct
  public void loadPresenters() {
    if (!template.exists(Query.query(Criteria.where("teamName").is("presenters")), ListOfBelts.class)) {
      template.save(buildInitialListOfBelts());
    }
  }

  private ListOfBelts buildInitialListOfBelts() {
    final ListOfBelts list = new ListOfBelts();
    final List<Belt> belts = new ArrayList<>();

    list.setTeamName("presenters");

    final Belt white = buildBelt("1", "white");
    final Belt yellow = buildBelt("2", "yellow");
    final Belt red = buildBelt("3", "red");
    final Belt brown = buildBelt("4", "brown");
    final Belt black = buildBelt("5", "black");

    belts.addAll(Arrays.asList(white, yellow, red, brown, black));

    list.setBelts(belts);
    return list;
  }

  private Belt buildBelt(final String id, final String namme) {
    final Belt belt = new Belt();
    belt.setId(id);
    belt.setName(namme);
    return belt;
  }


  public ListOfBelts getBeltForTeam(final String teamName) {
    return template.findOne(Query.query(Criteria.where("teamName").is(teamName)), ListOfBelts.class);
  }
}

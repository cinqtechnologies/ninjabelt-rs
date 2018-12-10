package br.com.cinq.dojo.ninjabelt.rs.service;

import br.com.cinq.dojo.ninjabelt.rs.model.Belt;
import br.com.cinq.dojo.ninjabelt.rs.model.ListOfBelts;
import br.com.cinq.dojo.ninjabelt.rs.model.Team;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class BeltsService {

  private final MongoTemplate template;

  private static final List<Belt> AVAILABLE_BELTS =
      Arrays.asList(
          buildBelt("1", "white"),
          buildBelt("2", "yellow"),
          buildBelt("3", "orange"),
          buildBelt("4", "brown"),
          buildBelt("5", "black")
      );

  public BeltsService(final MongoTemplate template) {
    this.template = template;
  }

  @PostConstruct
  public void loadPresenters() {
    template.remove(Query.query(Criteria.where("teamName").is("presenters")), ListOfBelts.class);
    template.save(buildInitialListOfBelts());
  }

  private ListOfBelts buildInitialListOfBelts() {
    final ListOfBelts list = new ListOfBelts();
    final List<Belt> belts = new ArrayList<>();

    list.setTeamName("presenters");
    belts.addAll(AVAILABLE_BELTS);

    list.setBelts(belts);
    return list;
  }

  private static Belt buildBelt(final String id, final String name) {
    final Belt belt = new Belt();
    belt.setId(id);
    belt.setName(name);
    return belt;
  }


  public ListOfBelts getBeltForTeam(final String teamName) {
    return template
        .findOne(Query.query(Criteria.where("teamName").is(teamName)), ListOfBelts.class);
  }

  public boolean addBeltToTeam(String teamName, String beltColor) {
    final ListOfBelts listOfBelts =
        template.findOne(Query.query(Criteria.where("teamName").is(teamName)), ListOfBelts.class);
    if (listOfBelts == null) {
      if (template.exists(Query.query(Criteria.where("name").is(teamName)), Team.class) &&
          AVAILABLE_BELTS.stream().anyMatch(belt -> belt.getName().equalsIgnoreCase(beltColor))
      ) {
        final ListOfBelts persistingListOfBelts = new ListOfBelts();
        persistingListOfBelts.setTeamName("teamName");
        persistingListOfBelts.setBelts(
            AVAILABLE_BELTS.stream()
                .filter(belt -> belt.getName().equalsIgnoreCase(beltColor)).collect(Collectors.toList())
        );
        return template.save(persistingListOfBelts) != null;
      } else {
        return false;
      }
    } else if (AVAILABLE_BELTS.stream().anyMatch(belt -> belt.getName().equalsIgnoreCase(beltColor))) {
      if (listOfBelts.getBelts()
          .stream().map(Belt::getName)
          .noneMatch(beltColor::equalsIgnoreCase)) {
        List<Belt> belts = listOfBelts.getBelts();
        final Belt persistingBelt = AVAILABLE_BELTS.stream()
            .filter(belt -> belt.getName().equalsIgnoreCase(beltColor))
            .findAny()
            .orElse(new Belt("???", beltColor));
        if (belts == null) {
          belts = new ArrayList<>();
          belts.add(persistingBelt);
          listOfBelts.setBelts(belts);
        } else {
          listOfBelts.getBelts().add(persistingBelt);
        }
        template.save(listOfBelts);
      }
      return true;
    }
    return false;
  }
}

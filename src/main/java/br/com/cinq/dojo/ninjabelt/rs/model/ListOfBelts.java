package br.com.cinq.dojo.ninjabelt.rs.model;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "listOfBelts")
public class ListOfBelts {

  @Id
  private String id;

  private String teamName;

  private List<Belt> belts;

  public List<Belt> getBelts() {
    return belts;
  }

  public void setBelts(List<Belt> belts) {
    this.belts = belts;
  }

  public String getTeamName() {
    return teamName;
  }

  public void setTeamName(String teamName) {
    this.teamName = teamName;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}

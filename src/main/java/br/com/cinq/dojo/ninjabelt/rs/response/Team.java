package br.com.cinq.dojo.ninjabelt.rs.response;

import br.com.cinq.dojo.ninjabelt.rs.response.Ninja;
import java.util.List;

public class Team {

  private String id;

  private Boolean isPresenter;

  private String name;

  private List<Ninja> ninjas;

  public Boolean getIsPresenter() {
    return isPresenter;
  }

  public void setIsPresenter(Boolean isPresenter) {
    this.isPresenter = isPresenter;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Ninja> getNinjas() {
    return ninjas;
  }

  public void setNinjas(List<Ninja> ninjas) {
    this.ninjas = ninjas;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}

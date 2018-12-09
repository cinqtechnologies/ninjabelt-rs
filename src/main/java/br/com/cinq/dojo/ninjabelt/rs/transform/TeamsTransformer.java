package br.com.cinq.dojo.ninjabelt.rs.transform;

import br.com.cinq.dojo.ninjabelt.rs.model.Team;
import br.com.cinq.dojo.ninjabelt.rs.response.ListOfTeams;
import java.util.Collections;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

@Component
public class TeamsTransformer {

  private final ModelMapper modelMapper;

  public TeamsTransformer(final ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }


  public ListOfTeams transform(List<Team> teams) {
    if(teams == null) {
      final ListOfTeams result = new ListOfTeams();
      result.setTeams(Collections.emptyList());
      return result;
    }
    final List<br.com.cinq.dojo.ninjabelt.rs.response.Team> transformedTeams =
        modelMapper.map(teams, new TypeToken<List<Team>>() {}.getType());
    final ListOfTeams listOfTeams = new ListOfTeams();
    listOfTeams.setTeams(transformedTeams);
    return listOfTeams;
  }
}
package br.com.cinq.dojo.ninjabelt.rs.transform;

import br.com.cinq.dojo.ninjabelt.rs.model.Team;
import br.com.cinq.dojo.ninjabelt.rs.response.ListOfBelts;
import br.com.cinq.dojo.ninjabelt.rs.response.ListOfTeams;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

@Component
public class BeltsTransformer {

  private final ModelMapper modelMapper;

  public BeltsTransformer(final ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }


  public ListOfBelts transform(br.com.cinq.dojo.ninjabelt.rs.model.ListOfBelts listOfBelts) {
    if(listOfBelts == null) {
      return null;
    }
    return modelMapper.map(listOfBelts, ListOfBelts.class);
  }
}
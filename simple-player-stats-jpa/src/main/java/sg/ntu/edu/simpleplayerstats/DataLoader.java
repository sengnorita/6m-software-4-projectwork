package sg.ntu.edu.simpleplayerstats;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import sg.ntu.edu.simpleplayerstats.entity.Player;
import sg.ntu.edu.simpleplayerstats.repository.PlayerRepository;

@Component
public class DataLoader {

  private PlayerRepository playerRepository;

  public DataLoader(PlayerRepository playerRepository) {
    this.playerRepository = playerRepository;
  }

  @PostConstruct
  public void loadData() {
    // Clear the db
    playerRepository.deleteAll();

    // Load player data
    Player player1 = Player.builder()
        .firstName("Lionel")
        .lastName("Messi")
        .footballclub("Paris Saint-Germain")
        .playerposition("Forward")
        .age(34)
        .nationality("Argentinian")
        .build();

    Player player2 = Player.builder()
        .firstName("Cristiano")
        .lastName("Ronaldo")
        .footballclub("Manchester United")
        .playerposition("Forward")
        .age(36)
        .nationality("Portuguese")
        .build();

    Player player3 = Player.builder()
        .firstName("Neymar")
        .lastName("Jr.")
        .footballclub("Paris Saint-Germain")
        .playerposition("Forward")
        .age(29)
        .nationality("Brazilian")
        .build();

    Player player4 = Player.builder()
        .firstName("Sergio")
        .lastName("Ramos")
        .footballclub("Paris Saint-Germain")
        .playerposition("Defender")
        .age(35)
        .nationality("Spanish")
        .build();

    Player player5 = Player.builder()
        .firstName("Mohamed")
        .lastName("Salah")
        .footballclub("Liverpool")
        .playerposition("Forward")
        .age(29)
        .nationality("Egyptian")
        .build();

    // Save players to db
    playerRepository.save(player1);
    playerRepository.save(player2);
    playerRepository.save(player3);
    playerRepository.save(player4);
    playerRepository.save(player5);

  }
}

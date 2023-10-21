package sg.ntu.edu.simpleplayerstats;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import sg.ntu.edu.simpleplayerstats.entity.Player;
import sg.ntu.edu.simpleplayerstats.exception.PlayerNotFoundException;
import sg.ntu.edu.simpleplayerstats.repository.PlayerRepository;
import sg.ntu.edu.simpleplayerstats.service.PlayerServiceImpl;

@SpringBootTest
public class PlayerServiceImplTest {

  @Mock
  private PlayerRepository playerRepository;

  @InjectMocks
  PlayerServiceImpl playerService;

  @Test
  public void createPlayerTest() {

    // 1. SETUP / ARRANGE
    // Player player = new Player();
    Player player = Player.builder()
        .firstName("Alfred")
        .lastName("Lim")
        .footballclub("Singapore Team")
        .playerposition("Middlefield")
        .age(46)
        .nationality("Singaporean")
        .build();

    // Mock the save method of player repository
    when((playerRepository.save(player))).thenReturn(player);

    // 2. EXECUTE
    // Call the method that we want to test
    Player savedPlayer = playerService.createPlayer(player);

    // 3. ASSERT
    assertEquals(player, savedPlayer, "The saved player should be the same as the input player");

    // Also verify that the save method was called
    verify(playerRepository, times(1)).save(player);
  }

  @Test
  public void getPlayerTest() {

    // 1. SETUP
    Player player = Player.builder()
        .firstName("Alfred")
        .lastName("Lim")
        .footballclub("Singapore Team")
        .playerposition("Middlefield")
        .age(46)
        .nationality("Singaporean")
        .build();

    Long playerId = 1L;

    when(playerRepository.findById(playerId)).thenReturn(Optional.of(player));

    // 2. EXECUTE
    Player retrievedPlayer = playerService.getPlayer(playerId);

    // 3. ASSERT
    assertEquals(player, retrievedPlayer, "The retrieved player should be the same as the input player");

  }

  @Test
  void getPlayerNotFoundTest() {
    Long playerId = 1L;
    when(playerRepository.findById(playerId)).thenReturn(Optional.empty());

    // Assert
    assertThrows(PlayerNotFoundException.class, () -> playerService.getPlayer(playerId));

  }

}

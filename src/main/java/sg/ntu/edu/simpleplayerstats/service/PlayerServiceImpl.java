package sg.ntu.edu.simpleplayerstats.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import lombok.AllArgsConstructor;
import sg.ntu.edu.simpleplayerstats.entity.Player;
import sg.ntu.edu.simpleplayerstats.entity.Statistic;
import sg.ntu.edu.simpleplayerstats.exception.PlayerNotFoundException;
import sg.ntu.edu.simpleplayerstats.repository.PlayerRepository;
import sg.ntu.edu.simpleplayerstats.repository.StatisticRepository;

@Service
@AllArgsConstructor
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private StatisticRepository statisticRepository;

    // Create
    @Override
    public Player createPlayer(Player player) {
        return playerRepository.save(player);
    }

    // Get One
    @Override
    public Player getPlayer(Long id) {
        return playerRepository.findById(id).orElseThrow(() -> new PlayerNotFoundException(id));
    }

    // Get All
    @Override
    public List<Player> getAllPlayers() {
        List<Player> allPlayers = playerRepository.findAll();
        return allPlayers;
    }

    // Update
    @Override
    public Player updatePlayer(Long id, Player player) {
        Player playerToUpdate = playerRepository.findById(id).orElseThrow(() -> new PlayerNotFoundException(id));

        playerToUpdate.setFirstName(player.getFirstName());
        playerToUpdate.setLastName(player.getLastName());
        playerToUpdate.setFootballclub(player.getFootballclub());
        playerToUpdate.setPlayerposition(player.getPlayerposition());
        playerToUpdate.setAge(player.getAge());
        playerToUpdate.setNationality(player.getNationality());
        return playerRepository.save(playerToUpdate);
    }

    // Delete
    @Override
    public void deletePlayer(Long id) {
        playerRepository.deleteById(id);
    }

    @Override
    public Statistic addStatisticToPlayer(Long id, Statistic statistic) {
        Player selectedPlayer = playerRepository.findById(id).orElseThrow(() -> new PlayerNotFoundException(id));

        statistic.setPlayer(selectedPlayer);
        return statisticRepository.save(statistic);
    }

    @Override
    public List<Player> searchPlayers(String firstName) {
        List<Player> foundPlayers = playerRepository.findByFirstName(firstName);
        return foundPlayers;
    }


}

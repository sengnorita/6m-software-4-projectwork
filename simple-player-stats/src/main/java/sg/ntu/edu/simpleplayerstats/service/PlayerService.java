package sg.ntu.edu.simpleplayerstats.service;

import java.util.List;

import sg.ntu.edu.simpleplayerstats.entity.Player;
import sg.ntu.edu.simpleplayerstats.entity.Statistic;

public interface PlayerService {
    Player createPlayer(Player player);

    Player getPlayer(Long id);

    List<Player> getAllPlayers();

    Player updatePlayer(Long id, Player player);

    void deletePlayer(Long id);

    Statistic addStatisticToPlayer(Long id, Statistic statistic);

    List<Player> searchPlayers(String playerName);
}

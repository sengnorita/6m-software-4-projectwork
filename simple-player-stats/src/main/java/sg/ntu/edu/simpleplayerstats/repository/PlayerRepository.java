package sg.ntu.edu.simpleplayerstats.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.ntu.edu.simpleplayerstats.entity.Player;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    List<Player> findByFirstName(String firstName);
}

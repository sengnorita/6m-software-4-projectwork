package sg.ntu.edu.simpleplayerstats.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.ntu.edu.simpleplayerstats.entity.Statistic;

public interface StatisticRepository extends JpaRepository<Statistic, Long> {

}

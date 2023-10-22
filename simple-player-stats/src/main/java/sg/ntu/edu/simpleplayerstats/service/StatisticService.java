package sg.ntu.edu.simpleplayerstats.service;

import java.util.List;

import sg.ntu.edu.simpleplayerstats.entity.Statistic;

public interface StatisticService {
    // CREATE
    Statistic saveStatistic(Statistic statistic);

    // READ
    Statistic getStatistic(Long id);

    List<Statistic> getAllStatistics();

    // UPDATE
    Statistic updateStatistic(Long id, Statistic statistic);

    // DELETE
    void deleteStatistic(Long id);
}

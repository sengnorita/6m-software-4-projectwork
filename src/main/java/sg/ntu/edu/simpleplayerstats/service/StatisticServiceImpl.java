package sg.ntu.edu.simpleplayerstats.service;

import org.springframework.stereotype.Service;

import java.util.List;

import lombok.AllArgsConstructor;
import sg.ntu.edu.simpleplayerstats.entity.Statistic;
import sg.ntu.edu.simpleplayerstats.exception.StatisticNotFoundException;
import sg.ntu.edu.simpleplayerstats.repository.StatisticRepository;

@Service
@AllArgsConstructor
public class StatisticServiceImpl implements StatisticService {

    private StatisticRepository statisticRepository;

    @Override
    public Statistic saveStatistic(Statistic statistic) {
        return statisticRepository.save(statistic);
    }

    @Override
    public Statistic getStatistic(Long id) {
        return statisticRepository.findById(id).orElseThrow(() -> new StatisticNotFoundException(id));
    }

    @Override
    public List<Statistic> getAllStatistics() {
        return statisticRepository.findAll();
    }

    @Override
    public Statistic updateStatistic(Long id, Statistic statistic) {
        // Load the statistic
        Statistic statisticToUpdate = statisticRepository.findById(id)
                .orElseThrow(() -> new StatisticNotFoundException(id));
        statisticToUpdate.setGoals(statistic.getGoals());
        statisticToUpdate.setMatchDate(statistic.getMatchDate());
        statisticToUpdate.setOpposition(statistic.getOpposition());
        statisticToUpdate.setAssists(statistic.getAssists());
        return statisticRepository.save(statisticToUpdate);
    }

    @Override
    public void deleteStatistic(Long id) {
        statisticRepository.deleteById(id);
    }
}

package sg.ntu.edu.simpleplayerstats.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import sg.ntu.edu.simpleplayerstats.entity.Statistic;
import sg.ntu.edu.simpleplayerstats.service.StatisticService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/statistic")
public class StatisticController {

    private static final Logger logger = LoggerFactory.getLogger(StatisticController.class);
    
    @Autowired
    private StatisticService statisticService;

    
    public StatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    // Create
    @PostMapping("")
    public ResponseEntity<Statistic> createStatistic(@RequestBody Statistic statistic) {
        logger.info("Creating a new statistic: {}", statistic.toString());
        Statistic newStatistic = statisticService.saveStatistic(statistic);
        return new ResponseEntity<>(newStatistic, HttpStatus.CREATED);
    }
    

    // Read All
    @GetMapping("")
    public ResponseEntity<List<Statistic>> getAllStatistics() {
        List<Statistic> allStatistics = statisticService.getAllStatistics();
        return new ResponseEntity<>(allStatistics, HttpStatus.OK);
    }

    // Read One
    @GetMapping("{id}")
    public ResponseEntity<Statistic> getStatistic(@PathVariable Long id) {
        logger.info("Fetching statistic with ID: {}", id);
        Statistic foundStatistic = statisticService.getStatistic(id);
        return new ResponseEntity<>(foundStatistic, HttpStatus.OK);
    }
    

    // Update
    @PutMapping("{id}")
    public ResponseEntity<Statistic> updateStatistic(@PathVariable Long id, @RequestBody Statistic statistic) {
        logger.info("Updating statistic with ID {}: {}", id, statistic.toString());
        Statistic updatedStatistic = statisticService.updateStatistic(id, statistic);
        return new ResponseEntity<>(updatedStatistic, HttpStatus.OK);
    }
    

    // Delete
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteStatistic(@PathVariable Long id) {
        logger.info("Deleting statistic with ID: {}", id);
        statisticService.deleteStatistic(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    public static void main(String[] args) {
        logger.info("🟢 Starting Soccer Stats API");
        logger.debug("❓ An exception occurred");
        logger.warn("🟠 Test Warning");
        logger.error("🔴 Error Warning");
        SpringApplication.run(PlayerController.class, args);
    }
}

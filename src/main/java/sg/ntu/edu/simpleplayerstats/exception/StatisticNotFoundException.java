package sg.ntu.edu.simpleplayerstats.exception;

public class StatisticNotFoundException extends RuntimeException {
    public StatisticNotFoundException(Long id) {
        super("Could not find statisic " + id);
    }

}
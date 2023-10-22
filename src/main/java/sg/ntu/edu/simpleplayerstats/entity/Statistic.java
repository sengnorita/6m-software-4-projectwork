package sg.ntu.edu.simpleplayerstats.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import org.hibernate.exception.ConstraintViolationException;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

// import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "statistic")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
// @Builder
public class Statistic {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "goals")
  private int goals;

  @Column(name = "assists")
  private int assists;

  @Column(name = "opposition")
  private String opposition;

  @Column(name = "match_date")
  @PastOrPresent(message = "Match date should not be in the future")
  private LocalDate matchDate;

  @ManyToOne(optional = false)
  @JoinColumn(name = "player_id", referencedColumnName = "id")
  private Player player;

  // Constructors, getters, and setters can be added as needed.
}

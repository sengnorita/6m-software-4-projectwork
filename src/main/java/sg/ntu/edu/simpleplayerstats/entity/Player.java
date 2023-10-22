package sg.ntu.edu.simpleplayerstats.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "player")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    @NotBlank(message = "First name is mandatory")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "footballclub")
    @NotBlank(message = "footballclub should be valid")
    private String footballclub;

    @Column(name = "player_position")
    private String playerposition;

    @Column(name = "age")
    private int age;

    @Column(name = "nationality")
    private String nationality;

    // Bi-directional relationship
    // One player can have many statistics
    @JsonIgnore
    @OneToMany(mappedBy = "player")
    private List<Statistic> statistics;

    public Player(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Builder
    public Player(String firstName, String lastName, String footballclub, String playerposition, int age,
            String nationality) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.footballclub = footballclub;
        this.playerposition = playerposition;
        this.age = age;
        this.nationality = nationality;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((footballclub == null) ? 0 : footballclub.hashCode());
        result = prime * result + ((playerposition == null) ? 0 : playerposition.hashCode());
        result = prime * result + age;
        result = prime * result + ((nationality == null) ? 0 : nationality.hashCode());
        result = prime * result + ((statistics == null) ? 0 : statistics.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Player other = (Player) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (firstName == null) {
            if (other.firstName != null)
                return false;
        } else if (!firstName.equals(other.firstName))
            return false;
        if (lastName == null) {
            if (other.lastName != null)
                return false;
        } else if (!lastName.equals(other.lastName))
            return false;
        if (footballclub == null) {
            if (other.footballclub != null)
                return false;
        } else if (!footballclub.equals(other.footballclub))
            return false;
        if (playerposition == null) {
            if (other.playerposition != null)
                return false;
        } else if (!playerposition.equals(other.playerposition))
            return false;
        if (age != other.age)
            return false;
        if (nationality == null) {
            if (other.nationality != null)
                return false;
        } else if (!nationality.equals(other.nationality))
            return false;
        if (statistics == null) {
            if (other.statistics != null)
                return false;
        } else if (!statistics.equals(other.statistics))
            return false;
        return true;
    }
}

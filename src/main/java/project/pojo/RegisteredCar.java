package project.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Entity(name = "registered_car")
public class RegisteredCar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "registered_car_seq")
    private long id;
    private String carNumber;
    private Timestamp timestamp;

}

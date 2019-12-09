package project.pojo;

import com.querydsl.core.annotations.QueryEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;


@Data
@Entity(name = "registered_car")
@AllArgsConstructor
@NoArgsConstructor
@QueryEntity
public class RegisteredCar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "registered_car_seq")
    private long id;
    private String carNumber;
    private Timestamp timestamp;

    public RegisteredCar(String carNumber, Timestamp timestamp) {
        this.carNumber = carNumber;
        this.timestamp = timestamp;
    }
}

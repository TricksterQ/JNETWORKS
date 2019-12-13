package project.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
public class ResponseCar {

    private String carNumber;
    private OffsetDateTime timestamp;
}

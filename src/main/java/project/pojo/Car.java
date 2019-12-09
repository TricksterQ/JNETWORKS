package project.pojo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Data
public class Car {

    @NotEmpty(message = "Number can't be empty")
    @NotNull(message = "Number can't be null")
    @Pattern(regexp = "[ A-Z0-9\\-]{4,16}", message = "Invalid car number")
    private String carNumber;

}

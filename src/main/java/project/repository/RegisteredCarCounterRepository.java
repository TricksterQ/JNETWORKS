package project.repository;

import org.springframework.data.repository.CrudRepository;
import project.pojo.RegisteredCar;

public interface RegisteredCarCounterRepository extends CrudRepository<RegisteredCar, Long> {
}

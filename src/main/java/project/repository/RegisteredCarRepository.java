package project.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import project.pojo.RegisteredCar;

public interface RegisteredCarRepository extends
        CrudRepository<RegisteredCar, Long>,
        QuerydslPredicateExecutor<RegisteredCar> {

}

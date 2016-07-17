package be.jidoka.employee.domain.employee;

import be.jidoka.employee.architecture.ddd.AggregateID;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepository extends MongoRepository<Employee, AggregateID> {
}

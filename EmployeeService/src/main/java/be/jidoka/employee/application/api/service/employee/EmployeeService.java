package be.jidoka.employee.application.api.service.employee;

import java.util.List;

public interface EmployeeService {

    EmployeeDTO findOne(String id);

    List<EmployeeDTO> findAll();

    EmployeeDTO addConsultancyAssignment(String id, CreateConsultancyAssignmentCommand project);

}

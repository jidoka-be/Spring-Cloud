package be.jidoka.employee.controller.employee;

import be.jidoka.employee.application.api.service.employee.CreateConsultancyAssignmentCommand;
import be.jidoka.employee.application.api.service.employee.EmployeeDTO;
import be.jidoka.employee.application.api.service.employee.EmployeeService;
import be.jidoka.employee.architecture.Mapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(method = GET)
    public List<BusinessCardDTO> findAll() {
        return businessCardMapper().map(employeeService.findAll());
    }

    @RequestMapping(value = "/{id}", method = GET)
    public EmployeeDTO findOne(@PathVariable("id") String id) {
        return employeeService.findOne(id);
    }

    @RequestMapping(value = "/{id}/project", method = POST)
    public EmployeeDTO addAssignment(@PathVariable("id") String id, @RequestBody CreateConsultancyAssignmentCommand consultancyAssignment) {
        return employeeService.addConsultancyAssignment(id, consultancyAssignment);
    }

    private Mapper<EmployeeDTO, BusinessCardDTO> businessCardMapper() {
        return (employee) -> BusinessCardDTO.of(employee.getId(), employee.getName(), employee.getRole());
    }

    private static class BusinessCardDTO {
        private final String id;
        private final String name;
        private final String function;

        private BusinessCardDTO(String id, String name, String function) {
            this.id = id;
            this.name = name;
            this.function = function;
        }

        public static BusinessCardDTO of(String id, String name, String function) {
            return new BusinessCardDTO(id, name, function);
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getFunction() {
            return function;
        }
    }

}

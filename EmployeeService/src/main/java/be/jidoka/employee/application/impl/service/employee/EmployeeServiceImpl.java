package be.jidoka.employee.application.impl.service.employee;

import be.jidoka.employee.application.api.service.employee.CreateConsultancyAssignmentCommand;
import be.jidoka.employee.application.api.service.employee.EmployeeDTO;
import be.jidoka.employee.application.api.service.employee.EmployeeService;
import be.jidoka.employee.application.api.service.project.ProjectClient;
import be.jidoka.employee.application.api.service.project.ProjectDTO;
import be.jidoka.employee.architecture.Mapper;
import be.jidoka.employee.architecture.ddd.AggregateID;
import be.jidoka.employee.domain.employee.ConsultancyAssignment;
import be.jidoka.employee.domain.employee.Employee;
import be.jidoka.employee.domain.employee.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final Mapper<Employee, EmployeeDTO> employeeDTOMapper;
    private final ProjectClient projectClient;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, Mapper<Employee, EmployeeDTO> employeeDTOMapper, ProjectClient projectClient) {
        this.employeeRepository = employeeRepository;
        this.employeeDTOMapper = employeeDTOMapper;
        this.projectClient = projectClient;
    }

    @Override
    public EmployeeDTO findOne(String id) {
        return employeeDTOMapper.map(employeeRepository.findOne(AggregateID.from(id)));
    }

    @Override
    public List<EmployeeDTO> findAll() {
        return employeeDTOMapper.map(employeeRepository.findAll());
    }

    @Override
    public EmployeeDTO addConsultancyAssignment(String id, CreateConsultancyAssignmentCommand consultancyAssignment) {
        final Employee employee = employeeRepository.findOne(AggregateID.from(id));

        final ProjectDTO project = projectClient.create(consultancyAssignment.getProject());
        if (!project.equals(ProjectDTO.EMPTY)) {
            employee.addProject(ConsultancyAssignment.of(project.getId(), consultancyAssignment.getDescription(), consultancyAssignment.getStartDate(), consultancyAssignment.getEndDate()));
            employeeRepository.save(employee);
        }
        return employeeDTOMapper.map(employee);
    }

    /*
        private final RestTemplate restTemplate;

        ----------------------------------------
        - Consuming REST API with RestTemplate -
        ----------------------------------------

        return restTemplate.exchange(
                "http://localhost:9200/mbti/{type}",
                 HttpMethod.GET,
                 null,
                 new ParameterizedTypeReference<MbtiDTO>() {
                 },
                 (Object) type
         ).getBody();

        -----------------------------------------------------------------
        - Consuming REST API with RestTemplate - with Service Discovery -
        -----------------------------------------------------------------

        return restTemplate.exchange(
                "http://mbti-service/mbti/{type}",
                 HttpMethod.GET,
                 null,
                 new ParameterizedTypeReference<MbtiDTO>() {
                 },
                 (Object) type
         ).getBody();

     */

}

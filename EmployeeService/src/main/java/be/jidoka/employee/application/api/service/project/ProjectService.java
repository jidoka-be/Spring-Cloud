package be.jidoka.employee.application.api.service.project;

import be.jidoka.employee.application.api.service.employee.CreateProjectCommand;
import be.jidoka.employee.configuration.ProjectConfiguration;
import be.jidoka.employee.infrastructure.ProjectGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class ProjectService implements ProjectClient {

    private final ProjectConfiguration project;
    private final ProjectGateway projectGateway;

    @Autowired
    ProjectService(ProjectConfiguration project, ProjectGateway projectGateway) {
        this.project = project;
        this.projectGateway = projectGateway;
    }

    @Override
    public ProjectDTO get(Long id) {
        return ProjectDTO.of(project.getDescription(), company());
    }

    @Override
    public ProjectDTO create(CreateProjectCommand project) {
        projectGateway.write(project);
        return ProjectDTO.EMPTY;
    }

    private CompanyDTO company() {
        return CompanyDTO.of(project.getCompanyName(), project.getCompanyDescription(), project.getCompanyEmployeeCount(), project.getCompanyWebsite(), project.getCompanySector());
    }

}

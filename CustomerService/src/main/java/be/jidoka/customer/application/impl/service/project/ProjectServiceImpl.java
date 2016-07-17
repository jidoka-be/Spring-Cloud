package be.jidoka.customer.application.impl.service.project;

import be.jidoka.customer.application.api.exception.EntityAlreadyExistsException;
import be.jidoka.customer.application.api.service.project.CreateProjectCommand;
import be.jidoka.customer.application.api.service.project.ProjectDTO;
import be.jidoka.customer.application.api.service.project.ProjectService;
import be.jidoka.customer.architecture.Mapper;
import be.jidoka.customer.domain.company.CompanyRepository;
import be.jidoka.customer.domain.project.Project;
import be.jidoka.customer.domain.project.ProjectFactory;
import be.jidoka.customer.domain.project.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final Mapper<Project, ProjectDTO> projectDTOMapper;
    private final CompanyRepository companyRepository;
    private final ProjectFactory projectFactory;

    public ProjectServiceImpl(ProjectRepository projectRepository,
                              Mapper<Project, ProjectDTO> projectDTOMapper,
                              CompanyRepository companyRepository,
                              ProjectFactory projectFactory) {
        this.projectRepository = projectRepository;
        this.projectDTOMapper = projectDTOMapper;
        this.companyRepository = companyRepository;
        this.projectFactory = projectFactory;
    }

    @Override
    public ProjectDTO findOne(Long id) {
        return projectDTOMapper.map(projectRepository.findOne(id));
    }

    @Override
    public ProjectDTO create(CreateProjectCommand project) {
        final Long companyId = project.getCompany();
        if (projectRepository.findByNameAndCompanyId(project.getName(), companyId).isPresent()) {
            throw new EntityAlreadyExistsException(Project.class);
        }
        return projectDTOMapper.map(projectRepository.save(projectFactory.create(project.getName(), project.getDescription(), companyRepository.findOne(companyId))));
    }

    @Override
    public List<ProjectDTO> findByName(String name) {
        return projectDTOMapper.map(projectRepository.findByNameContainingIgnoreCase(name));
    }

}

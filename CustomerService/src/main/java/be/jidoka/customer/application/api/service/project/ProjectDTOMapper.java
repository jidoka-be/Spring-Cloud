package be.jidoka.customer.application.api.service.project;

import be.jidoka.customer.application.api.service.company.CompanyDTO;
import be.jidoka.customer.architecture.Mapper;
import be.jidoka.customer.domain.company.Company;
import be.jidoka.customer.domain.project.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static be.jidoka.customer.application.api.service.project.ProjectDTO.ProjectDTOBuilder.projectDTOWith;

@Component
public class ProjectDTOMapper implements Mapper<Project, ProjectDTO> {

    private final Mapper<Company, CompanyDTO> companyDTOMapper;

    @Autowired
    private ProjectDTOMapper(Mapper<Company, CompanyDTO> companyDTOMapper) {
        this.companyDTOMapper = companyDTOMapper;
    }

    @Override
    public ProjectDTO map(Project project) {
        return projectDTOWith()
                .id(project.getId())
                .name(project.getName())
                .description(project.getDescription())
                .company(companyDTOMapper.map(project.getCompany()))
                .build();
    }

}

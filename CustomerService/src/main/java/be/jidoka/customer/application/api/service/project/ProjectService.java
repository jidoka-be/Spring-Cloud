package be.jidoka.customer.application.api.service.project;

import java.util.List;

public interface ProjectService {

    ProjectDTO findOne(Long id);

    ProjectDTO create(CreateProjectCommand project);

    List<ProjectDTO> findByName(String name);

}

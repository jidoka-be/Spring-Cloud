package be.jidoka.customer.controller.project;

import be.jidoka.customer.application.api.service.project.CreateProjectCommand;
import be.jidoka.customer.application.api.service.project.ProjectDTO;
import be.jidoka.customer.application.api.service.project.ProjectService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("project")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @RequestMapping(value = "/{id}", method = GET)
    public ProjectDTO findOne(@PathVariable("id") Long id) {
        return projectService.findOne(id);
    }

    @RequestMapping(value = "/name/{name}", method = GET)
    public List<ProjectDTO> findByName(@PathVariable("name") String name) {
        return projectService.findByName(name);
    }

    @RequestMapping(method = POST)
    public ProjectDTO create(@RequestBody CreateProjectCommand project) {
        return projectService.create(project);
    }

}

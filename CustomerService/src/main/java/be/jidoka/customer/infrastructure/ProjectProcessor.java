package be.jidoka.customer.infrastructure;

import be.jidoka.customer.application.api.service.project.CreateProjectCommand;
import be.jidoka.customer.application.api.service.project.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.MessageEndpoint;

@MessageEndpoint
public class ProjectProcessor {

    private final ProjectService projectService;

    @Autowired
    public ProjectProcessor(ProjectService projectService) {
        this.projectService = projectService;
    }

    @StreamListener(Sink.INPUT)
    public void createProject(CreateProjectCommand project) {
        projectService.create(project);
    }

}

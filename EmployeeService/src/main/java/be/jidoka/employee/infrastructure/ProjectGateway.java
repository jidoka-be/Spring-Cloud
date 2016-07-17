package be.jidoka.employee.infrastructure;

import be.jidoka.employee.application.api.service.employee.CreateProjectCommand;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface ProjectGateway {

    @Gateway(requestChannel = Source.OUTPUT)
    void write(CreateProjectCommand project);

}

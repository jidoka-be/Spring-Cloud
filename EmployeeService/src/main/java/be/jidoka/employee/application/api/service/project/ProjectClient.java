package be.jidoka.employee.application.api.service.project;

import be.jidoka.employee.application.api.service.employee.CreateProjectCommand;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@FeignClient(value = "customer-service", fallback = ProjectService.class, configuration = ProjectClient.Configuration.class)
public interface ProjectClient {

    @RequestMapping(value = "/project/{id}", method = GET)
    ProjectDTO get(@PathVariable("id") Long id);

    @RequestMapping(value = "/project", method = POST)
    ProjectDTO create(@RequestBody CreateProjectCommand project);

    @org.springframework.context.annotation.Configuration
    class Configuration {
        @Bean
        ErrorDecoder errorDecoder() {
            return new ProjectClientErrorDecoder();
        }
    }

    class ProjectClientErrorDecoder implements ErrorDecoder {
        @Override
        public Exception decode(String methodKey, Response response) {
            if (response.status() == HttpStatus.CONFLICT.value()) {
                return new ProjectAlreadyExistsException(response.reason());
            }
            return new Default().decode(methodKey, response);
        }

        public static class ProjectAlreadyExistsException extends HystrixBadRequestException {
            public ProjectAlreadyExistsException(String message) {
                super(message);
            }
        }
    }

}

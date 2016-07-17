package be.jidoka.employee;

import be.jidoka.employee.domain.employee.Address;
import be.jidoka.employee.domain.employee.ConsultancyAssignment;
import be.jidoka.employee.domain.employee.ContactInformation;
import be.jidoka.employee.domain.employee.Employee;
import be.jidoka.employee.domain.employee.EmployeeRepository;
import be.jidoka.employee.domain.employee.MaritalStatus;
import be.jidoka.employee.domain.role.Role;
import be.jidoka.employee.domain.role.RoleRepository;
import be.jidoka.employee.infrastructure.CustomerServiceChannels;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.IntegrationComponentScan;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static be.jidoka.employee.domain.employee.CoWorker.CoWorkerBuilder.coWorkerWith;
import static be.jidoka.employee.domain.employee.FreeLancer.FreeLancerBuilder.freeLancerWith;
import static be.jidoka.employee.domain.employee.SocialMedia.linkedIn;
import static be.jidoka.employee.domain.employee.SocialMedia.skype;
import static be.jidoka.employee.domain.employee.SocialMedia.twitter;

@EnableFeignClients
@EnableCircuitBreaker
@EnableDiscoveryClient
@SpringBootApplication
@IntegrationComponentScan
@EnableBinding(CustomerServiceChannels.class)
public class EmployeeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeServiceApplication.class, args);
	}

    @Bean
    AlwaysSampler alwaysSampler() {
        return new AlwaysSampler();
    }

    @Bean
    ObjectMapper objectMapper() {
        return new ObjectMapper()
                .findAndRegisterModules()
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Bean
    CommandLineRunner runner(RoleRepository roleRepository, EmployeeRepository employeeRepository) {
        return args -> {
            roleRepository.save(rolesExampleData());

            employeeRepository.deleteAll();
            employeeRepository.save(employeeExampleData());
        };
    }

    private List<Role> rolesExampleData() {
        return Arrays.asList(
                Role.of("Java Solution Engineer"),
                Role.of("Solution Engineer"),
                Role.of("CTO")
        );
    }

    private List<Employee> employeeExampleData() {
        return Arrays.asList(
                jos(),
                leo(),
                bart(),
                gaston()
        );
    }

    private Employee jos() {
        return coWorkerWith()
                .firstName("Jos")
                .lastName("Flos")
                .contactInformation(ContactInformation.of("jos.flos@jidoka.be", "+32 490 23 11 34", Arrays.asList(linkedIn("josflos"), skype("js.fs"), twitter("jos_flos"))))
                .dateOfBirth(LocalDate.of(1982, 6, 18))
                .address(Address.of("Pleinstraat", "1", "4", "3500", "Hasselt"))
                .mbti("ISTJ")
                .role(Role.of("Java Solution Engineer"))
                .assignments(Arrays.asList(ConsultancyAssignment.of(2L, "A new module, e09 IBRS Item information, needs to be developed. Introducing accounting on small packets. As core developer of the e09 module, Jos is responsible for the initial set up of the new module. During development he represents the development team in conference calls with the international postal members.", LocalDate.of(2013, 8, 1), LocalDate.of(2014, 8, 31)), ConsultancyAssignment.of(1L, "Jos is a core developer for several domains: Postal Events datastore, Track & Trace and Master Data Management. He is responsible for the implementation and challenging of the captured software requirements. Jos introduces and coaches on functional programming. Refactoring the existing code to a more functional style (using Google guava).", LocalDate.of(2014, 9, 1), LocalDate.of(2015, 12, 31)), ConsultancyAssignment.of(3L, "As lead developer, Jos is responsible for the initial setup of the project.  Besides feature development, Jos coaches on TDD, clean code and continuous integration within the team. Assuring a high code quality.", LocalDate.of(2016, 1, 1), LocalDate.of(2016, 1, 10))))
                .maritalStatus(MaritalStatus.SINGLE)
                .build();
    }

    private Employee leo() {
        return coWorkerWith()
                .firstName("Leo")
                .lastName("Martin")
                .contactInformation(ContactInformation.of("leo.martin@jidoka.be", "+32 460 93 11 82", Arrays.asList(linkedIn("leo-martin"), skype("leo.martin"), twitter("leomartin"))))
                .dateOfBirth(LocalDate.of(1992, 3, 14))
                .address(Address.of("Kerkstraat", "5", "3500", "Hasselt"))
                .mbti("INTJ")
                .role(Role.of("Java Solution Engineer"))
                .assignments(Arrays.asList(ConsultancyAssignment.of(1L, null, LocalDate.of(2014, 11, 1))))
                .maritalStatus(MaritalStatus.MARRIED)
                .build();
    }

    private Employee bart() {
        return freeLancerWith()
                .firstName("Bart")
                .lastName("Peeters")
                .contactInformation(ContactInformation.of("bart@it.be", null, Arrays.asList(linkedIn("bartpeerters"))))
                .address(Address.of("Medialaan", "3", "3500", "Hasselt"))
                .mbti("INTJ")
                .role(Role.of("Solution Engineer"))
                .companyName("Peeters BVBA")
                .vatNumber("BE0621363290")
                .build();
    }

    private Employee gaston() {
        return coWorkerWith()
                .firstName("Gaston")
                .lastName("Berghmans")
                .contactInformation(ContactInformation.of("gaston.berghmans@jidoka.be", "+32 480 60 20 15", Arrays.asList(linkedIn("gastonberghmans"), skype("gastonberghmans"), twitter("gastonberghmans"))))
                .dateOfBirth(LocalDate.of(1962, 11, 10))
                .address(Address.of("Medialaan", "4", "3500", "Hasselt"))
                .mbti("INTJ")
                .role(Role.of("CTO"))
                .assignments(Arrays.asList(ConsultancyAssignment.of(1L, null, LocalDate.of(2014, 1, 1))))
                .maritalStatus(MaritalStatus.MARRIED)
                .numberOfChildren(3)
                .build();
    }

}
